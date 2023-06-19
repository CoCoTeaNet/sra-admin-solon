package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.model.system.dto.role.RoleAddParam;
import net.cocotea.admin.model.system.dto.role.RolePageParam;
import net.cocotea.admin.model.system.dto.role.RoleUpdateParam;
import net.cocotea.admin.model.system.vo.RoleMenuVO;
import net.cocotea.admin.model.system.vo.RoleVO;
import net.cocotea.admin.service.system.IRoleService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author jwss
 */
@Mapping("/system/role")
@Controller
public class RoleController {
    @Inject
    private IRoleService roleService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/add")
    public ApiResult<String> add(@Body RoleAddParam param) throws BusinessException {
        boolean b = roleService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/update")
    public ApiResult<String> update(@Body RoleUpdateParam param) throws BusinessException {
        boolean b = roleService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/delete/{id}")
    public ApiResult<String> delete(@Path String id) throws BusinessException {
        boolean b = roleService.delete(id);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> idList) throws BusinessException {
        boolean b = roleService.deleteBatch(idList);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/grantPermissionsByRoleId")
    public ApiResult<String> grantPermissionsByRoleId(@Body List<RoleMenuVO> param) throws BusinessException {
        boolean b = roleService.grantPermissionsByRoleId(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/listByPage")
    public ApiResult<Page<RoleVO>> listByPage(@Body RolePageParam param) throws BusinessException {
        Page<RoleVO> p = roleService.listByPage(param);
        return ApiResult.ok(p);
    }
}
