package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharPool;
import net.cocotea.admin.api.system.model.dto.SysDictionaryAddDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryPageDTO;
import net.cocotea.admin.api.system.model.dto.SysDictionaryUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysDictionary;
import net.cocotea.admin.api.system.model.po.SysUser;
import net.cocotea.admin.api.system.model.vo.SysDictionaryVO;
import net.cocotea.admin.api.system.service.SysDictionaryService;
import net.cocotea.admin.common.enums.IsEnum;
import net.cocotea.admin.common.model.ApiPage;
import net.cocotea.admin.common.util.TreeBuilder;
import org.noear.solon.annotation.Component;
import org.noear.solon.data.annotation.Tran;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.solon.annotation.Db;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Db("db1")
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysDictionaryAddDTO addDTO) {
        SysDictionary sysDictionary = sqlToyLazyDao.convertType(addDTO, SysDictionary.class);
        if (sysDictionary.getParentId() == null) {
            sysDictionary.setParentId(BigInteger.ZERO);
        }
        if (sysDictionary.getSort() == null) {
            sysDictionary.setSort(0);
        }
        Object o = sqlToyLazyDao.save(sysDictionary);
        return o != null;
    }

    @Tran
    @Override
    public boolean deleteBatch(List<BigInteger> idList) {
        idList.forEach(this::delete);
        return !idList.isEmpty();
    }

    @Override
    public boolean update(SysDictionaryUpdateDTO param) {
        SysDictionary sysDictionary = sqlToyLazyDao.convertType(param, SysDictionary.class);
        Long update = sqlToyLazyDao.update(sysDictionary);
        return update != null;
    }

    @Override
    public ApiPage<SysDictionaryVO> listByPage(SysDictionaryPageDTO dto) {
        return null;
    }

    @Override
    public List<SysDictionaryVO> listByTree(SysDictionaryPageDTO dto) {
        return new TreeBuilder<SysDictionaryVO>().get(findList(dto.getSysDictionary()));
    }

    private List<SysDictionaryVO> findList(SysDictionaryVO sysDictionaryVO) {
        List<SysDictionary> dictionaryList = sqlToyLazyDao.findBySql("sys_dictionary_findList", BeanUtil.toBean(sysDictionaryVO, SysDictionary.class));
        // 查询关联的用户名称
        List<BigInteger> userIds = dictionaryList.stream().map(SysDictionary::getCreateBy).collect(Collectors.toList());
        List<SysUser> sysUsers = sqlToyLazyDao.loadByIds(SysUser.class, userIds);
        Map<BigInteger, String> userMap = sysUsers
                .stream()
                .collect(Collectors.toMap(SysUser::getId, i -> i.getUsername().concat(String.valueOf(CharPool.AT)).concat(i.getNickname())));
        List<SysDictionaryVO> dictionaryVOList = new ArrayList<>(dictionaryList.size());
        for (SysDictionary dictionary : dictionaryList) {
            SysDictionaryVO dictionaryVO = Convert.convert(SysDictionaryVO.class, dictionary);
            if (dictionary.getCreateBy() != null) {
                String username = userMap.get(dictionary.getCreateBy());
                dictionaryVO.setCreateBy(username);
            }
            dictionaryVOList.add(dictionaryVO);
        }
        return dictionaryVOList;
    }

    @Override
    public boolean delete(BigInteger id) {
        SysDictionary sysDictionary = new SysDictionary().setId(id).setIsDeleted(IsEnum.Y.getCode());
        Long update = sqlToyLazyDao.update(sysDictionary);
        if (update <= 0) {
            return false;
        }
        // 获取子节点
        List<SysDictionary> list = sqlToyLazyDao.findBySql("sys_dictionary_findList", new SysDictionary().setParentId(id));
        if (!list.isEmpty()) {
            // 存在子节点，删除子节点
            list.forEach(item -> delete(item.getId()));
        }
        return true;
    }

}
