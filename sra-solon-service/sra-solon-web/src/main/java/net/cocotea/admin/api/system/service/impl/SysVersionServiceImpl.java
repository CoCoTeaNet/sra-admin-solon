package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.model.dto.SysVersionAddDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionPageDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysVersion;
import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import net.cocotea.admin.api.system.service.SysVersionService;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Service
public class SysVersionServiceImpl implements SysVersionService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysVersionAddDTO param) throws BusinessException {
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
    public boolean update(SysVersionUpdateDTO param) throws BusinessException {
        SysVersion sysVersion = Convert.convert(SysVersion.class, param);
        Long count = sqlToyLazyDao.update(sysVersion);
        return count > 0;
    }

    @Override
    public Page<SysVersionVO> listByPage(SysVersionPageDTO param) throws BusinessException {
        Page<SysVersionVO> page = sqlToyLazyDao.findPageBySql(param, "system_version_findByPageParam", param.getVersion());
        return page;
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        return sqlToyLazyDao.delete(new SysVersion().setId(id)) > 0;
    }
}
