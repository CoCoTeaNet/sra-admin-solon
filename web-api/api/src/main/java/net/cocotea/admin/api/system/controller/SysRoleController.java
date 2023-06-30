package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysRoleAddDTO;
import net.cocotea.admin.api.system.model.dto.SysRolePageDTO;
import net.cocotea.admin.api.system.model.dto.SysRoleUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysRoleMenuVO;
import net.cocotea.admin.api.system.model.vo.SysRoleVO;
import net.cocotea.admin.api.system.service.SysRoleService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author jwss
 */
@Mapping("/system/role")
@Controller
public class SysRoleController {
    @Inject
    private SysRoleService roleService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/add")
    public ApiResult<String> add(@Body SysRoleAddDTO param) throws BusinessException {
        boolean b = roleService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/update")
    public ApiResult<String> update(@Body SysRoleUpdateDTO param) throws BusinessException {
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
    public ApiResult<String> grantPermissionsByRoleId(@Body List<SysRoleMenuVO> param) throws BusinessException {
        boolean b = roleService.grantPermissionsByRoleId(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/listByPage")
    public ApiResult<Page<SysRoleVO>> listByPage(@Body SysRolePageDTO param) throws BusinessException {
        Page<SysRoleVO> p = roleService.listByPage(param);
        return ApiResult.ok(p);
    }
}
