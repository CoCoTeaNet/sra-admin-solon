package net.cocotea.admin.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import net.cocotea.admin.system.param.dictionary.DictionaryAddParam;
import net.cocotea.admin.system.param.dictionary.DictionaryPageParam;
import net.cocotea.admin.system.param.dictionary.DictionaryUpdateParam;
import net.cocotea.admin.system.service.IDictionaryService;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.system.vo.DictionaryVO;
import org.noear.solon.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author jwss
 * @date 2022-3-22
 */
@Controller
@Mapping("/system/dictionary")
public class DictionaryController {
    @Inject
    private IDictionaryService dictionaryService;

    @Post
    @Mapping("/add")
    @SaCheckPermission("system:dictionary:add")
    public ApiResult<String> add(@Body DictionaryAddParam param) throws BusinessException {
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
    public ApiResult<String> update(@Body DictionaryUpdateParam param) throws BusinessException {
        boolean b = dictionaryService.update(param);
        return ApiResult.flag(b);
    }

    @Mapping("/listByTree")
    @SaCheckPermission("system:dictionary:listByTree")
    public ApiResult<?> listByTree(@Body DictionaryPageParam param) {
        Collection<DictionaryVO> list = dictionaryService.listByTree(param);
        return ApiResult.ok(list);
    }
}
