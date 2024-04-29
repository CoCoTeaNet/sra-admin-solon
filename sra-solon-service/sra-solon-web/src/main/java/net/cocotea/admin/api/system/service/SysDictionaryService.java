package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.SysDictionaryAddDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryPageDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryUpdateDTO;
import net.cocotea.admin.api.system.model.vo.SysDictionaryVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

import java.util.Collection;

/**
 * 字典 接口服务类
 * @author jwss
 * @date 2022-3-22
 */
public interface SysDictionaryService extends IBaseService<Page<SysDictionaryVO>, SysDictionaryPageDTO, SysDictionaryAddDTO, SysDictionaryUpdateDTO> {

    /**
     * 获取树形结构
     * @param param 分页参数
     * @return 分页对象
     */
    Collection<SysDictionaryVO> listByTree(SysDictionaryPageDTO param);
}
