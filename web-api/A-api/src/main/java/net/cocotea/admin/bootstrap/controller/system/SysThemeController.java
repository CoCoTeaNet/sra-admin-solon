package net.cocotea.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.system.param.theme.SysThemeUpdateParam;
import net.cocotea.admin.system.service.ISysThemeService;
import net.cocotea.admin.system.vo.SysThemeVO;
import org.noear.solon.annotation.*;

@Mapping("/system/theme")
@Controller
public class SysThemeController {
    @Inject
    private ISysThemeService sysThemeService;

    @SaCheckLogin
    @Post
    @Mapping("/updateByUser")
    public ApiResult<?> updateByUser(@Body SysThemeUpdateParam param) {
        boolean b = sysThemeService.updateByUser(param);
        return ApiResult.ok(b);
    }

    @SaCheckLogin
    @Post
    @Mapping("/loadByUser")
    public ApiResult<?> loadByUser() {
        SysThemeVO b = sysThemeService.loadByUser();
        return ApiResult.ok(b);
    }
}
