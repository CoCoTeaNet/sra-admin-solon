package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.api.system.model.dto.SysThemeUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysThemeVO;
import net.cocotea.admin.api.system.service.SysThemeService;
import org.noear.solon.annotation.*;

@Mapping("/system/theme")
@Controller
public class SysThemeController {
    @Inject
    private SysThemeService sysThemeService;

    @SaCheckLogin
    @Post
    @Mapping("/updateByUser")
    public ApiResult<?> updateByUser(@Body SysThemeUpdateDTO param) {
        boolean b = sysThemeService.updateByUser(param);
        return ApiResult.ok(b);
    }

    @SaCheckLogin
    @Get
    @Mapping("/loadByUser")
    public ApiResult<?> loadByUser() {
        SysThemeVO b = sysThemeService.loadByUser();
        return ApiResult.ok(b);
    }
}
