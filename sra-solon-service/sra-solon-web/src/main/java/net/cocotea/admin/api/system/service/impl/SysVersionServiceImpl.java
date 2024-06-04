package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.model.dto.SysVersionAddDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionPageDTO;
import net.cocotea.admin.api.system.model.dto.SysVersionUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysVersion;
import net.cocotea.admin.api.system.model.vo.SysVersionVO;
import net.cocotea.admin.api.system.service.SysVersionService;
import net.cocotea.admin.common.model.ApiPage;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.data.annotation.Tran;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.noear.solon.annotation.Component;
import org.sagacity.sqltoy.solon.annotation.Db;

import java.math.BigInteger;
import java.util.List;

@Component
public class SysVersionServiceImpl implements SysVersionService {

    @Db("db1")
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysVersionAddDTO param) throws BusinessException {
        SysVersion sysVersion = Convert.convert(SysVersion.class, param);
        Object save = sqlToyLazyDao.save(sysVersion);
        return save != null;
    }

    @Tran
    @Override
    public boolean deleteBatch(List<BigInteger> idList) throws BusinessException {
        for (BigInteger s : idList) {
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
    public ApiPage<SysVersionVO> listByPage(SysVersionPageDTO pageDTO) throws BusinessException {
        Page<SysVersionVO> page = sqlToyLazyDao.findPageBySql(pageDTO, "sys_version_findList", pageDTO.getSysVersion());
        return ApiPage.rest(page, SysVersionVO.class);
    }

    @Override
    public boolean delete(BigInteger id) throws BusinessException {
        return sqlToyLazyDao.delete(new SysVersion().setId(id)) > 0;
    }
}
