package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.api.system.model.dto.SysVersionPageDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionUpdateDTO;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysVersionAddDTO;
import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import net.cocotea.admin.api.system.service.SysVersionService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Mapping("/system/version")
@Controller
public class VersionController {
    @Inject
    private SysVersionService versionService;
    
    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/add")
    @Post
    public ApiResult<?> add(@Body SysVersionAddDTO param) throws BusinessException {
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
    public ApiResult<?> update(@Body SysVersionUpdateDTO param) throws BusinessException {
        boolean b = versionService.update(param);
        return ApiResult.flag(b);
    }

    @SaCheckLogin
    @Post
    @Mapping("/listByPage")
    public ApiResult<?> listByPage(@Body SysVersionPageDTO param) throws BusinessException {
        Page<SysVersionVO> r = versionService.listByPage(param);
        return ApiResult.ok(r);
    }
    
}
