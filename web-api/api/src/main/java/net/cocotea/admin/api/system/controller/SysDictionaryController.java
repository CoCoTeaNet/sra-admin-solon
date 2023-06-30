package net.cocotea.admin.api.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import net.cocotea.admin.api.system.model.dto.SysDictionaryAddDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryPageDTO;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.api.system.model.dto.SysDictionaryUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysDictionaryVO;
import net.cocotea.admin.api.system.service.SysDictionaryService;
import org.noear.solon.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author jwss
 * @date 2022-3-22
 */
@Controller
@Mapping("/system/dictionary")
public class SysDictionaryController {
    @Inject
    private SysDictionaryService dictionaryService;

    @Post
    @Mapping("/add")
    @SaCheckPermission("system:dictionary:add")
    public ApiResult<String> add(@Body SysDictionaryAddDTO param) throws BusinessException {
        boolean b = dictionaryService.add(param);
        return ApiResult.flag(b);
    }

    @Mapping("/deleteBatch")
    @SaCheckPermission("system:dictionary:deleteBatch")
    public ApiResult<String> deleteBatch(@Body List<String> list) throws BusinessException {
        boolean b = dictionaryService.deleteBatch(list);
        return ApiResult.flag(b);
    }

    @Mapping("/update")
    @SaCheckPermission("system:dictionary:update")
    public ApiResult<String> update(@Body SysDictionaryUpdateDTO param) throws BusinessException {
        boolean b = dictionaryService.update(param);
        return ApiResult.flag(b);
    }

    @Mapping("/listByTree")
    @SaCheckPermission("system:dictionary:listByTree")
    public ApiResult<?> listByTree(@Body SysDictionaryPageDTO param) {
        Collection<SysDictionaryVO> list = dictionaryService.listByTree(param);
        return ApiResult.ok(list);
    }
}
