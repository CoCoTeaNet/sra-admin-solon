package net.cocotea.admin.api.controller.system;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.service.dto.system.user.UserAddParam;
import net.cocotea.admin.service.dto.system.user.UserPageParam;
import net.cocotea.admin.service.dto.system.user.UserUpdateParam;
import net.cocotea.admin.service.vo.system.UserVO;
import net.cocotea.admin.service.core.system.IUserService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

/**
 * @author jwss
 */
@Mapping("/system/user")
@Controller
public class UserController {
    @Inject
    private IUserService userService;

    @SaCheckPermission("system:user:add")
    @Post
    @Mapping("/add")
    public ApiResult<String> add(@Body UserAddParam param) throws BusinessException {
        boolean b = userService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckPermission("system:user:update")
    @Post
    @Mapping("/update")
    public ApiResult<String> update(@Body UserUpdateParam param) throws BusinessException {
        boolean b = userService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckPermission("system:user:delete")
    @Post
    @Mapping("/delete/{id}")
    public ApiResult<String> delete(@Path String id) throws BusinessException {
        boolean b = userService.delete(id);
        return ApiResult.flag(b);
    }

    @SaCheckPermission("system:user:delete")
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> idList) throws BusinessException {
        boolean b = userService.deleteBatch(idList);
        return ApiResult.flag(b);
    }

    @SaCheckPermission("system:user:listByPage")
    @Post
    @Mapping("/listByPage")
    public ApiResult<Page<UserVO>> listByPage(@Body UserPageParam param) throws BusinessException {
        Page<UserVO> list = userService.listByPage(param);
        return ApiResult.ok(list);
    }

    @SaCheckLogin
    @Get
    @Mapping("/getDetail")
    public ApiResult<UserVO> getDetail() {
        UserVO vo = userService.getDetail();
        return ApiResult.ok(vo);
    }
}
