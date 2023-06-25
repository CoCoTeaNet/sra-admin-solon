package net.cocotea.admin.service.core.system.impl;

import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.util.GenerateDsUtils;
import net.cocotea.admin.common.constant.CharConstant;
import net.cocotea.admin.service.dto.system.dictionary.DictionaryAddParam;
import net.cocotea.admin.service.dto.system.dictionary.DictionaryPageParam;
import net.cocotea.admin.service.dto.system.dictionary.DictionaryUpdateParam;
import net.cocotea.admin.data.model.SysDictionary;
import net.cocotea.admin.service.vo.system.DictionaryVO;
import net.cocotea.admin.service.core.system.IDictionaryService;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.sagacity.sqltoy.utils.StringUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author jwss
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(DictionaryAddParam param) {
        SysDictionary sysDictionary = sqlToyLazyDao.convertType(param, SysDictionary.class);
        if (StringUtil.isBlank(sysDictionary.getParentId())) {
            sysDictionary.setParentId(CharConstant.ZERO);
        }
        Object o = sqlToyLazyDao.save(sysDictionary);
        return o != null;
    }

    @Tran
    @Override
    public boolean deleteBatch(List<String> idList) {
        idList.forEach(this::delete);
        return idList.size() > 0;
    }

    @Override
    public boolean update(DictionaryUpdateParam param) {
        SysDictionary sysDictionary = sqlToyLazyDao.convertType(param, SysDictionary.class);
        Long update = sqlToyLazyDao.update(sysDictionary);
        return update != null;
    }

    @Override
    public Page<DictionaryVO> listByPage(DictionaryPageParam param) {
        return sqlToyLazyDao.findPageBySql(param, "system_dictionary_findByPageParam", param.getDictionaryVO());
    }

    @Override
    public Collection<DictionaryVO> listByTree(DictionaryPageParam param) {
        List<DictionaryVO> list = sqlToyLazyDao.findBySql("system_dictionary_findByPageParam", param.getDictionaryVO());
        GenerateDsUtils<DictionaryVO> dsUtils = new GenerateDsUtils<>();
        return dsUtils.buildTreeDefault(list).values();
    }

    @Override
    public boolean delete(String id) {
        SysDictionary sysDictionary = new SysDictionary().setId(id).setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
        Long update = sqlToyLazyDao.update(sysDictionary);
        if (update <= 0) {
            return false;
        }
        // 获取子节点
        HashMap<String, Object> paramsMap = new HashMap<>(Character.UPPERCASE_LETTER);
        paramsMap.put("parentId", id);
        List<SysDictionary> list = sqlToyLazyDao.findBySql(
                "select id from sys_dictionary where #[PARENT_ID=:parentId] and DELETE_STATUS=1",
                paramsMap, SysDictionary.class);
        if (list.size() > 0) {
            // 存在子节点，删除子节点
            list.forEach(item -> delete(item.getId()));
        }
        return true;
    }

}
