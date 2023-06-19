package net.cocotea.admin.service.system;

import net.cocotea.admin.common.service.IBaseService;
import net.cocotea.admin.model.system.dto.dictionary.DictionaryAddParam;
import net.cocotea.admin.model.system.dto.dictionary.DictionaryPageParam;
import net.cocotea.admin.model.system.dto.dictionary.DictionaryUpdateParam;
import net.cocotea.admin.model.system.vo.DictionaryVO;
import org.sagacity.sqltoy.model.Page;

import java.util.Collection;

/**
 * 字典 接口服务类
 * @author jwss
 * @date 2022-3-22
 */
public interface IDictionaryService extends IBaseService<Page<DictionaryVO>, DictionaryPageParam, DictionaryAddParam, DictionaryUpdateParam> {

    /**
     * 获取树形结构
     * @param param 分页参数
     * @return 分页对象
     */
    Collection<DictionaryVO> listByTree(DictionaryPageParam param);
}