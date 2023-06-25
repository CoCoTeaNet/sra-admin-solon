package net.cocotea.admin.service.core.system.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.data.model.SysVersion;
import net.cocotea.admin.service.dto.system.version.VersionAddParam;
import net.cocotea.admin.service.dto.system.version.VersionPageParam;
import net.cocotea.admin.service.dto.system.version.VersionUpdateParam;
import net.cocotea.admin.service.vo.system.VersionVO;
import net.cocotea.admin.service.core.system.IVersionService;
import net.cocotea.admin.common.model.BusinessException;
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
        SysVersion sysVersion = Convert.convert(SysVersion.class, param);
        Object save = sqlToyLazyDao.save(sysVersion);
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
        SysVersion sysVersion = Convert.convert(SysVersion.class, param);
        Long count = sqlToyLazyDao.update(sysVersion);
        return count > 0;
    }

    @Override
    public Page<VersionVO> listByPage(VersionPageParam param) throws BusinessException {
        Page<VersionVO> page = sqlToyLazyDao.findPageBySql(param, "system_version_findByPageParam", param.getVersion());
        return page;
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        return sqlToyLazyDao.delete(new SysVersion().setId(id)) > 0;
    }
}
