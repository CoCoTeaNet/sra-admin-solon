package net.cocotea.admin.system.service.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.system.entity.Version;
import net.cocotea.admin.system.service.IVersionService;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.system.param.version.VersionAddParam;
import net.cocotea.admin.system.param.version.VersionPageParam;
import net.cocotea.admin.system.param.version.VersionUpdateParam;
import net.cocotea.admin.system.vo.VersionVO;
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
