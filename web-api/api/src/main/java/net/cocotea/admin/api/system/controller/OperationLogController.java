package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysOperationLogPageDTO;
import net.cocotea.admin.api.system.model.vo.SysOperationLogVO;
import net.cocotea.admin.api.system.service.SysOperationLogService;
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
    private SysOperationLogService operationLogService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/listByPage")
    public ApiResult<Page<SysOperationLogVO>> pageApiResult(@Body SysOperationLogPageDTO param) throws BusinessException {
        Page<SysOperationLogVO> p = operationLogService.listByPage(param);
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
