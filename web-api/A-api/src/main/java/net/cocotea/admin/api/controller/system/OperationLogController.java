package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.model.system.dto.log.OperationLogPageParam;
import net.cocotea.admin.model.system.vo.OperationLogVO;
import net.cocotea.admin.service.system.IOperationLogService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * 系统操作日志 接口控制器
 *
 * @author jwss
 * @date 2022-4-29 16:37:01
 */
@Controller
@Mapping("/system/operationLog")
public class OperationLogController {
    @Inject
    private IOperationLogService operationLogService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/listByPage")
    public ApiResult<Page<OperationLogVO>> pageApiResult(@Body OperationLogPageParam param) throws BusinessException {
        Page<OperationLogVO> p = operationLogService.listByPage(param);
        return ApiResult.ok(p);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> ids) throws BusinessException {
        boolean b = operationLogService.deleteBatch(ids);
        return ApiResult.flag(b);
    }
}
