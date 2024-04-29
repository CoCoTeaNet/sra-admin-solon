package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.io.FileUtil;
import net.cocotea.admin.api.system.model.dto.SysFileAddDTO;
import net.cocotea.admin.api.system.model.dto.SysFilePageDTO;
import net.cocotea.admin.api.system.model.dto.SysFileUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysFile;
import net.cocotea.admin.api.system.model.vo.SysFileVO;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.api.system.service.SysFileService;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import cn.hutool.core.convert.Convert;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统文件表
 *
 * @author CoCoTea 572315466@qq.com
 * @since 1.2.1 2022-12-28
 */
@Service
public class SysFileServiceImpl implements SysFileService {

    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysFileAddDTO param) {
        SysFile sysFile = Convert.convert(SysFile.class, param);
        Object save = sqlToyLazyDao.save(sysFile);
        return save != null;
    }

    @Tran
    @Override
    public boolean deleteBatch(List<String> param) {
        List<SysFile> sysFileList = new ArrayList<>(param.size());
        for (String id : param) {
            sysFileList.add(new SysFile().setId(id).setDeleteStatus(DeleteStatusEnum.DELETE.getCode()));
        }
        return sqlToyLazyDao.updateAll(sysFileList) > 0;
    }

    @Override
    public boolean update(SysFileUpdateDTO param) {
        SysFile sysFile = Convert.convert(SysFile.class, param);
        Long count = sqlToyLazyDao.update(sysFile);
        return count > 0;
    }

    @Override
    public Page<SysFileVO> listByPage(SysFilePageDTO param) {
        Page<SysFileVO> page = sqlToyLazyDao.findPageBySql(param, "system_sysFile_findByPageParam", param.getFile());
        return page;
    }

    @Override
    public boolean delete(String id) {
        return sqlToyLazyDao.update(new SysFile().setId(id).setDeleteStatus(DeleteStatusEnum.DELETE.getCode())) > 0;
    }

    @Override
    public Page<SysFileVO> recycleBinPage(SysFilePageDTO param) {
        Page<SysFileVO> page = sqlToyLazyDao.findPageBySql(param, "system_sysFile_delete_findByPageParam", param.getFile());
        return page;
    }

    @Tran
    @Override
    public boolean recycleBinDeleteBatch(List<String> param) {
        List<SysFile> sysFileList = new ArrayList<>(param.size());
        for (String id : param) {
            SysFile sysFile = sqlToyLazyDao.load(new SysFile().setId(id));
            if (sysFile != null) {
                FileUtil.del(sysFile.getRealPath());
            }
            sysFileList.add(new SysFile().setId(id));
        }
        return sqlToyLazyDao.deleteAll(sysFileList) > 0;
    }

    @Override
    public boolean recoveryBatch(List<String> param) {
        List<SysFile> sysFileList = new ArrayList<>(param.size());
        for (String id : param) {
            sysFileList.add(new SysFile().setId(id).setDeleteStatus(DeleteStatusEnum.NOT_DELETE.getCode()));
        }
        return sqlToyLazyDao.updateAll(sysFileList) > 0;
    }
}