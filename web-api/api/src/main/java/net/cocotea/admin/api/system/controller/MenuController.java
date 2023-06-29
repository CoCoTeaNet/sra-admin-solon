package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysMenuAddDTO;
import net.cocotea.admin.api.system.model.dto.SysMenuPageDTO;
import net.cocotea.admin.api.system.model.dto.SysMenuUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysMenuVO;
import net.cocotea.admin.api.system.service.SysMenuService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;
import java.util.Collection;
import java.util.List;

/**
 * @date 2022-1-16 15:37:26
 * @author jwss
 */
@Controller
@Mapping("/system/menu")
public class MenuController {
    @Inject
    private SysMenuService menuService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("add")
    public ApiResult<String> add(@Body SysMenuAddDTO param) throws BusinessException {
        boolean b = menuService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> idList) throws BusinessException {
        boolean b = menuService.deleteBatch(idList);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("update")
    public ApiResult<String> update(@Body SysMenuUpdateDTO param) throws BusinessException {
        boolean b = menuService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByPage")
    public ApiResult<Page<SysMenuVO>> listByPage(@Body SysMenuPageDTO pageParam) throws BusinessException {
        Page<SysMenuVO> menus = menuService.listByPage(pageParam);
        return ApiResult.ok(menus);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByTree")
    public ApiResult<?> listByTree(@Body SysMenuPageDTO param) {
        Collection<SysMenuVO> menus = menuService.listByTree(param);
        return ApiResult.ok(menus);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByTreeAsRoleSelection")
    public ApiResult<?> listByTreeAsRoleSelection(@Body SysMenuPageDTO pageParam) {
        Collection<SysMenuVO> menus = menuService.listByTreeAsRoleSelection(pageParam);
        return ApiResult.ok(menus);
    }

    @Get
    @Mapping("listByRoleId/{roleId}")
    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    public ApiResult<List<SysMenuVO>> listByRoleId(@Path String roleId) {
        List<SysMenuVO> menus = menuService.listByRoleId(roleId);
        return ApiResult.ok(menus);
    }
}
