package net.cocotea.admin.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import net.cocotea.admin.api.system.service.SysLogService;
import net.cocotea.admin.common.constant.RedisKeyConst;
import net.cocotea.admin.common.enums.ApiResultEnum;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.model.NotLogException;
import net.cocotea.admin.common.service.RedisService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AppFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(AppFilter.class);

    @Inject
    private SysLogService sysLogService;

    @Inject
    private RedisService redisService;

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        //1.开始计时（用于计算响应时长）
        long start = System.currentTimeMillis();
        try {
            apiLimitAccessTimes(ctx.realIp());

            chain.doFilter(ctx);

            //2.未处理设为404状态
            if (!ctx.getHandled()) {
                ctx.status(404);
            }

            if (ctx.status() == 404) {
                ApiResult<Object> result = new ApiResult<>(ApiResultEnum.NOT_FOUNT.getCode(), null, ApiResultEnum.NOT_FOUNT.getDesc());
                ctx.render(result);
            }

            onlineUsersRenewal();
        } catch (Exception e) {
            ctx.status(ApiResultEnum.SUCCESS.getCode());
            //4.异常捕促与控制
            logger.error(e.getMessage());
            boolean saveLogFlag = true;

            ApiResult<?> result;

            if (e instanceof NotLoginException) {
                logger.error("登录失效异常:{}", e.getMessage());
                result = ApiResult.error(ApiResultEnum.NOT_LOGIN.getCode(), ApiResultEnum.NOT_LOGIN.getDesc());
            } else if (e instanceof NotPermissionException) {
                logger.error("权限不足异常:{}", e.getMessage());
                result = ApiResult.error(ApiResultEnum.NOT_PERMISSION.getCode(), ApiResultEnum.NOT_PERMISSION.getDesc());
            } else if (e instanceof BusinessException) {
                logger.error("业务逻辑异常: {}", e.getMessage());
                result = ApiResult.error(ApiResultEnum.ERROR.getCode(), ApiResultEnum.ERROR.getDesc());
            } else if (e instanceof NotLogException) {
                saveLogFlag = false;
                result = ApiResult.error(ApiResultEnum.ERROR.getCode(), ApiResultEnum.ERROR.getDesc());
            } else if (e instanceof NotRoleException) {
                logger.error("角色未知异常: {}", e.getMessage());
                result = ApiResult.error(ApiResultEnum.NOT_PERMISSION.getCode(), ApiResultEnum.NOT_PERMISSION.getDesc());
            } else {
                result = ApiResult.error(ApiResultEnum.ERROR.getDesc());
            }
            if (saveLogFlag) {
                saveLog(ctx);
            }

            ctx.render(result);
        }
        //5.获得接口响应时长
        long times = System.currentTimeMillis() - start;
        logger.info("用时：{}ms", times);
    }

    private void saveLog(Context ctx) throws BusinessException {
        logger.info("=============== handlerException-START ===============");
        logger.info("请求IP：{}", ctx.realIp());
        logger.info("请求地址：{}", ctx.path());
        logger.info("请求方式：{}", ctx.method());
        logger.info("=============== 请求内容-END ===============");
        sysLogService.saveErrorLog(ctx);
        // 保存登录日志与操作日志,如果没有登录不去保存
        if (StpUtil.isLogin()) {
            sysLogService.saveByLogType(LogTypeEnum.OPERATION.getCode(), ctx);
        }
    }

    /**
     * 在线用户续期
     */
    private void onlineUsersRenewal() {
        if (StpUtil.isLogin()) {
            if (StringUtil.isBlank(redisService.get(RedisKeyConst.ONLINE_USER))) {
                String loginId = String.valueOf(StpUtil.getLoginId());
                redisService.save(String.format(RedisKeyConst.ONLINE_USER, loginId), loginId, 30L);
            }
        }
    }

    @Inject("${sra-admin.once-visits}")
    Integer visits;

    /**
     * 接口访问限制：1秒内运行访问N次
     */
    private void apiLimitAccessTimes(String ip) throws BusinessException {
        if (StpUtil.isLogin()) {
            String redisKey = ip + ":" + StpUtil.getLoginId();
            String value = redisService.get(redisKey);
            if (StrUtil.isBlank(value)) {
                redisService.save(redisKey, String.valueOf(1), 1L);
                return;
            }
            if (Integer.parseInt(value) <= visits) {
                int count = Integer.parseInt(value);
                count++;
                redisService.set(redisKey, String.valueOf(count));
            } else {
                throw new BusinessException("操作过于频繁");
            }
        }
    }


}
