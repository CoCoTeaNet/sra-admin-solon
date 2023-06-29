package net.cocotea.admin.api.system.service.impl;

import net.cocotea.admin.api.system.model.dto.SysDictionaryAddDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryPageDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysDictionary;
import net.cocotea.admin.api.system.model.vo.SysDictionaryVO;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.util.GenerateDsUtils;
import net.cocotea.admin.common.constant.CharConstant;
import net.cocotea.admin.api.system.service.SysDictionaryService;
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
public class SysDictionaryServiceImpl implements SysDictionaryService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysDictionaryAddDTO param) {
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
    public boolean update(SysDictionaryUpdateDTO param) {
        SysDictionary sysDictionary = sqlToyLazyDao.convertType(param, SysDictionary.class);
        Long update = sqlToyLazyDao.update(sysDictionary);
        return update != null;
    }

    @Override
    public Page<SysDictionaryVO> listByPage(SysDictionaryPageDTO param) {
        return sqlToyLazyDao.findPageBySql(param, "system_dictionary_findByPageParam", param.getDictionary());
    }

    @Override
    public Collection<SysDictionaryVO> listByTree(SysDictionaryPageDTO param) {
        List<SysDictionaryVO> list = sqlToyLazyDao.findBySql("system_dictionary_findByPageParam", param.getDictionary());
        GenerateDsUtils<SysDictionaryVO> dsUtils = new GenerateDsUtils<>();
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
