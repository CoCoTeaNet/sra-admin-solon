package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckLogin;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.service.dto.system.theme.SysThemeUpdateParam;
import net.cocotea.admin.service.vo.system.SysThemeVO;
import net.cocotea.admin.service.core.system.ISysThemeService;
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
    @Get
    @Mapping("/loadByUser")
    public ApiResult<?> loadByUser() {
        SysThemeVO b = sysThemeService.loadByUser();
        return ApiResult.ok(b);
    }
}
