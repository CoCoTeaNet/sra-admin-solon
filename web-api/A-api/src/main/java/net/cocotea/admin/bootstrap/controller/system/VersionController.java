package net.cocotea.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.system.service.IVersionService;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.system.param.version.VersionAddParam;
import net.cocotea.admin.system.param.version.VersionPageParam;
import net.cocotea.admin.system.param.version.VersionUpdateParam;
import net.cocotea.admin.system.vo.VersionVO;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Mapping("/system/version")
@Controller
public class VersionController {
    @Inject
    private IVersionService versionService;
    
    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/add")
    @Post
    public ApiResult<?> add(@Body VersionAddParam param) throws BusinessException {
        boolean b = versionService.add(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/deleteBatch")
    public ApiResult<?> deleteBatch(@Body List<String> param) throws BusinessException {
        boolean b = versionService.deleteBatch(param);
        return ApiResult.flag(b);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Post
    @Mapping("/update")
    public ApiResult<?> update(@Body VersionUpdateParam param) throws BusinessException {
        boolean b = versionService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckLogin
    @Post
    @Mapping("/listByPage")
    public ApiResult<?> listByPage(@Body VersionPageParam param) throws BusinessException {
        Page<VersionVO> r = versionService.listByPage(param);
        return ApiResult.ok(r);
    }
    
}
