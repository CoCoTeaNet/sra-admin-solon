package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import net.cocotea.admin.api.system.model.dto.SysCaptchaDTO;
import net.cocotea.admin.api.system.model.dto.SysLoginDTO;
import net.cocotea.admin.api.system.model.vo.SysLoginUserVO;
import net.cocotea.admin.api.system.service.SysLogService;
import net.cocotea.admin.api.system.service.SysUserService;
import net.cocotea.admin.common.constant.RedisKeyConst;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.service.RedisService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;
import org.noear.solon.validation.annotation.Validated;

/**
 * 系统登录相关接口
 *
 * @author CoCoTea
 */
@Controller
@Mapping("/system")
public class SysLoginController {
    @Inject
    private SysUserService userService;

    @Inject
    private RedisService redisService;

    @Inject
    private SysLogService sysLogService;

    /**
     * 后台系统用户登录
     *
     * @param dto     {@link SysLoginDTO}
     * @param request {@link HttpServletRequest}
     * @return {@link ApiResult}
     */
    @Post
    @Mapping("/login")
    public ApiResult<?> login(@Validated @Body SysLoginDTO dto, Context context) throws BusinessException {
        userService.login(dto, context);
        // 保存登录日志
        sysLogService.saveByLogType(LogTypeEnum.LOGIN.getCode(), context);
        return ApiResult.ok();
    }

    /**
     * 后台系统用户退出登录
     *
     * @return {@link ApiResult}
     */
    @Post @Mapping("/logout")
    public ApiResult<?> logout() {
        // 删除权限缓存
        redisService.delete(String.format(RedisKeyConst.USER_PERMISSION, StpUtil.getLoginId()));
        redisService.delete(String.format(RedisKeyConst.ONLINE_USER, StpUtil.getLoginId()));
        StpUtil.logout();
        return ApiResult.ok();
    }

    /**
     * 获取用户登录信息
     *
     * @return {@link SysLoginUserVO}
     */
    @Get @Mapping("/loginInfo")
    public ApiResult<SysLoginUserVO> loginInfo() {
        SysLoginUserVO r = userService.loginUser();
        return ApiResult.ok(r);
    }

    /**
     * 获取后台登录验证码
     *
     * @param captchaDTO {@link SysCaptchaDTO}
     * @param request    {@link HttpServletRequest}
     * @return Base64格式验证码
     */
    @Post @Mapping("/captcha")
    public ApiResult<String> captcha(@Validated @Body SysCaptchaDTO captchaDTO, Context context) {
        // 生成圆圈干扰的验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        redisService.save(
                String.format(RedisKeyConst.VERIFY_CODE, captchaDTO.getCodeType(), context.ip()),
                captcha.getCode(),
                300L
        );
        return ApiResult.ok(captcha.getImageBase64());
    }
}
