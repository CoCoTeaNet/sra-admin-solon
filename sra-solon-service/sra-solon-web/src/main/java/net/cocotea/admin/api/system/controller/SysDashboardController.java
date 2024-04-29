package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.api.system.model.vo.SystemInfoVO;
import net.cocotea.admin.api.system.service.SysDashboardService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @date 2022-1-26 11:36:32
 * @author jwss
 */
@Controller
@Mapping("/system/dashboard")
public class SysDashboardController {
    @Inject
    private SysDashboardService dashboardService;

    @Get
    @Mapping("index")
    @SaCheckPermission("system:dashboard:getSystemInfo")
    public ApiResult<String> index() {
        return ApiResult.ok("Hello sss-rbac-admin.");
    }

    @Get
    @Mapping("getCount")
    @SaCheckPermission("system:dashboard:getCount")
    public ApiResult<List<Map<String, Object>>> getCount() {
        List<Map<String, Object>> count = dashboardService.getCount();
        return ApiResult.ok(count);
    }

    @Get
    @Mapping("getSystemInfo")
    @SaCheckPermission("system:dashboard:getSystemInfo")
    @SaCheckLogin
    public ApiResult<SystemInfoVO> getSystemInfo() throws UnknownHostException {
        SystemInfoVO vo = dashboardService.getSystemInfo();
        return ApiResult.ok(vo);
    }

}
