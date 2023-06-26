package net.cocotea.admin.api.controller.system;

import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;

/**
 * @date 2022-1-12 14:25:41
 * @author jwss
 */
@Controller
@Mapping("/system/test")
public class TestController {

    @Get
    @Mapping("index")
    public ApiResult<?> index() {
        return ApiResult.ok("Hello sss-rbac-admin.");
    }

    @Get
    @Mapping("permission")
    public ApiResult<?> permission() {
        return ApiResult.ok("has permission.");
    }

    @Get
    @Mapping("error")
    public ApiResult<?> error() throws BusinessException {
        throw new BusinessException("error");
    }
}
