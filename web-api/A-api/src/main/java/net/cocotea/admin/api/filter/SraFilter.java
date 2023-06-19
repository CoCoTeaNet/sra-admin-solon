package net.cocotea.admin.api.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import net.cocotea.admin.common.constant.RedisKey;
import net.cocotea.admin.common.enums.ApiResultEnum;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.model.NotLogException;
import net.cocotea.admin.common.service.IRedisService;
import net.cocotea.admin.service.system.IOperationLogService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.sagacity.sqltoy.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SraFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(SraFilter.class);

    @Inject
    private IOperationLogService operationLogService;

    @Inject
    private IRedisService redisService;

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        //1.开始计时（用于计算响应时长）
        long start = System.currentTimeMillis();
        try {
            chain.doFilter(ctx);

            //2.未处理设为404状态
            if(!ctx.getHandled()){
                ctx.status(404);
            }

            if (ctx.status() == 404) {
                ApiResult<Object> r = new ApiResult<>(ApiResultEnum.NOT_FOUNT.getCode(), null, ApiResultEnum.NOT_FOUNT.getDesc());
                ctx.output(JSONObject.toJSONString(r));
            }

            onlineUsersRenewal();
            boolean b = apiLimitAccessTimes(ctx.realIp());
            if (!b) {
                ctx.output(JSONObject.toJSONString(ApiResult.error("操作过于频繁.")));
            }
        } catch (Exception e) {
            //4.异常捕促与控制
            logger.error(e.getMessage());
            boolean saveLogFlag = true;
            if (e instanceof NotLoginException) {
                logger.error("登录失效异常:" + e.getMessage());
                ctx.status(ApiResultEnum.NOT_LOGIN.getCode());
                ctx.output(ApiResultEnum.NOT_LOGIN.getDesc());
            } else if (e instanceof NotPermissionException) {
                logger.error("权限不足异常:" + e.getMessage());
                ctx.status(ApiResultEnum.NOT_PERMISSION.getCode());
                ctx.output(ApiResultEnum.NOT_PERMISSION.getDesc());
            } else if (e instanceof BusinessException) {
                logger.error("业务逻辑异常: " + e.getMessage());
                ctx.status(ApiResultEnum.ERROR.getCode());
                ctx.output(ApiResultEnum.ERROR.getDesc());
            } else if (e instanceof NotLogException) {
                saveLogFlag = false;
                ctx.status(ApiResultEnum.ERROR.getCode());
                ctx.output(ApiResultEnum.ERROR.getDesc());
            } else if (e instanceof NotRoleException) {
                logger.error("角色未知异常: " + e.getMessage());
                ctx.status(ApiResultEnum.NOT_PERMISSION.getCode());
                ctx.output(ApiResultEnum.NOT_PERMISSION.getDesc());
            } else {
                ctx.status(ApiResultEnum.ERROR.getCode());
                ctx.output(ApiResultEnum.ERROR.getDesc());
            }
            if (saveLogFlag) {
                saveLog(ctx);
            }
        }
        //5.获得接口响应时长
        long times = System.currentTimeMillis() - start;
        logger.info("用时：{}ms", times);
    }

    private void saveLog(Context ctx) throws BusinessException {
        logger.info("=============== handlerException-START ===============");
        logger.info("请求IP：" + ctx.realIp());
        logger.info("请求地址：" + ctx.path());
        logger.info("请求方式：" + ctx.method());
        logger.info("=============== 请求内容-END ===============");
        operationLogService.saveErrorLog(ctx);
        // 保存登录日志与操作日志,如果没有登录不去保存
        if (StpUtil.isLogin()) {
            operationLogService.saveByLogType(LogTypeEnum.OPERATION.getCode());
        }
    }

    /**
     * 在线用户续期
     */
    private void onlineUsersRenewal() {
        if (StpUtil.isLogin()) {
            if (StringUtil.isBlank(redisService.get(RedisKey.ONLINE_USER))) {
                String loginId = String.valueOf(StpUtil.getLoginId());
                redisService.save(String.format(RedisKey.ONLINE_USER, loginId), loginId, 30L);
            }
        }
    }

    @Inject("${sra.api.visits}") Integer visits;

    /**
     * 接口访问限制：1秒内运行访问N次
     */
    private boolean apiLimitAccessTimes(String ip) {
        if (StpUtil.isLogin()) {
            String redisKey = ip + ":" + StpUtil.getLoginId();
            String value = redisService.get(redisKey);
            if (StrUtil.isBlank(value)) {
                redisService.save(redisKey, String.valueOf(1), 1L);
                return true;
            } if (Integer.parseInt(value) <= visits) {
                int count = Integer.parseInt(value);
                count++;
                redisService.set(redisKey, String.valueOf(count));
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


}
