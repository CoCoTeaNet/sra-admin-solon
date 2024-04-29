package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.alibaba.fastjson.JSONObject;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysUserAddDTO;
import net.cocotea.admin.api.system.model.dto.SysUserPageDTO;
import net.cocotea.admin.api.system.model.dto.SysUserUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysUserVO;
import net.cocotea.admin.api.system.service.SysUserService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author jwss
 */
@Mapping("/system/user")
@Controller
public class SysUserController {
    @Inject
    private SysUserService userService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/add")
    public ApiResult<String> add(@Body SysUserAddDTO param) throws BusinessException {
        boolean b = userService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckPermission("system:user:update")
    @Post
    @Mapping("/update")
    public ApiResult<String> update(@Body SysUserUpdateDTO param) throws BusinessException {
        boolean b = userService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/delete/{id}")
    public ApiResult<String> delete(@Path String id) throws BusinessException {
        boolean b = userService.delete(id);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> idList) throws BusinessException {
        boolean b = userService.deleteBatch(idList);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/listByPage")
    public ApiResult<Page<SysUserVO>> listByPage(@Body SysUserPageDTO param) throws BusinessException {
        Page<SysUserVO> list = userService.listByPage(param);
        return ApiResult.ok(list);
    }

    @SaCheckLogin
    @Get
    @Mapping("/getDetail")
    public ApiResult<SysUserVO> getDetail() {
        SysUserVO vo = userService.getDetail();
        return ApiResult.ok(vo);
    }

    @Post
    @Mapping("/doModifyPassword")
    public ApiResult<?> doModifyPassword(@Body JSONObject obj) throws BusinessException {
        boolean r = userService.doModifyPassword(obj.getString("oldPassword"), obj.getString("newPassword"));
        return ApiResult.ok(r);
    }
}
