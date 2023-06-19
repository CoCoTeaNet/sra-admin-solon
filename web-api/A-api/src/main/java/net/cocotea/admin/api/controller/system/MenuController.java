package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.model.system.dto.menu.MenuAddParam;
import net.cocotea.admin.model.system.dto.menu.MenuPageParam;
import net.cocotea.admin.model.system.dto.menu.MenuUpdateParam;
import net.cocotea.admin.model.system.vo.MenuVO;
import net.cocotea.admin.service.system.IMenuService;
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
    private IMenuService menuService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("add")
    public ApiResult<String> add(@Body MenuAddParam param) throws BusinessException {
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
    public ApiResult<String> update(@Body MenuUpdateParam param) throws BusinessException {
        boolean b = menuService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByPage")
    public ApiResult<Page<MenuVO>> listByPage(@Body MenuPageParam pageParam) throws BusinessException {
        Page<MenuVO> menus = menuService.listByPage(pageParam);
        return ApiResult.ok(menus);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByTree")
    public ApiResult<?> listByTree(@Body MenuPageParam param) {
        Collection<MenuVO> menus = menuService.listByTree(param);
        return ApiResult.ok(menus);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("listByTreeAsRoleSelection")
    public ApiResult<?> listByTreeAsRoleSelection(@Body MenuPageParam pageParam) {
        Collection<MenuVO> menus = menuService.listByTreeAsRoleSelection(pageParam);
        return ApiResult.ok(menus);
    }

    @Get
    @Mapping("listByRoleId/{roleId}")
    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    public ApiResult<List<MenuVO>> listByRoleId(@Path String roleId) {
        List<MenuVO> menus = menuService.listByRoleId(roleId);
        return ApiResult.ok(menus);
    }
}
