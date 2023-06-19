package net.cocotea.admin.service.system.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.model.system.dto.version.VersionAddParam;
import net.cocotea.admin.model.system.dto.version.VersionPageParam;
import net.cocotea.admin.model.system.dto.version.VersionUpdateParam;
import net.cocotea.admin.model.system.po.Version;
import net.cocotea.admin.model.system.vo.VersionVO;
import net.cocotea.admin.service.system.IVersionService;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Service
public class VersionServiceImpl implements IVersionService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(VersionAddParam param) throws BusinessException {
        Version version = Convert.convert(Version.class, param);
        Object save = sqlToyLazyDao.save(version);
        return save != null;
    }

    @Tran
    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        for (String s : idList) {
            delete(s);
        }
        return !idList.isEmpty();
    }

    @Override
    public boolean update(VersionUpdateParam param) throws BusinessException {
        Version version = Convert.convert(Version.class, param);
        Long count = sqlToyLazyDao.update(version);
        return count > 0;
    }

    @Override
    public Page<VersionVO> listByPage(VersionPageParam param) throws BusinessException {
        Page<VersionVO> page = sqlToyLazyDao.findPageBySql(param, "system_version_findByPageParam", param.getVersion());
        return page;
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        return sqlToyLazyDao.delete(new Version().setId(id)) > 0;
    }
}
