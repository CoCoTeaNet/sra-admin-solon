package net.cocotea.admin.api.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.model.dto.SysOperationLogAddDTO;
import net.cocotea.admin.api.system.model.dto.SysOperationLogPageDTO;
import net.cocotea.admin.api.system.model.dto.SysOperationLogUpdateDTO;
import net.cocotea.admin.api.system.model.po.SysOperationLog;
import net.cocotea.admin.api.system.model.vo.SysOperationLogVO;
import net.cocotea.admin.common.enums.LogTypeEnum;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.common.enums.OperationStatusEnum;
import net.cocotea.admin.api.system.service.SysOperationLogService;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.core.handle.Context;
import org.noear.solon.extend.sqltoy.annotation.Db;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CoCoTea
 */
@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {
    @Db
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(SysOperationLogAddDTO param) throws BusinessException {
        SysOperationLog sysOperationLog = Convert.convert(SysOperationLog.class, param);
        Object save = sqlToyLazyDao.save(sysOperationLog);
        return save != null;
    }

    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        idList.forEach(this::delete);
        return idList.size() > 0;
    }

    @Override
    public boolean update(SysOperationLogUpdateDTO param) throws BusinessException {
        return false;
    }

    @Override
    public Page<SysOperationLogVO> listByPage(SysOperationLogPageDTO param) throws BusinessException {
        Page<SysOperationLogVO> vo = sqlToyLazyDao.findPageBySql(param, "system_operationLog_findByPageParam", param.getOperationLog());
        return vo;
    }

    @Override
    public boolean delete(String id) {
        return sqlToyLazyDao.deleteByIds(SysOperationLog.class, id) > 0;
    }

    @Override
    public void saveByLogType(Integer logType) throws BusinessException {
        SysOperationLogAddDTO logAddParam = new SysOperationLogAddDTO();
        logAddParam.setIpAddress(Context.current().realIp());
        logAddParam.setLogType(logType);
        logAddParam.setRequestWay(Context.current().method());
        logAddParam.setLogNumber(System.currentTimeMillis());
        logAddParam.setOperator(String.valueOf(StpUtil.getLoginId()));
        logAddParam.setOperationStatus(OperationStatusEnum.SUCCESS.getCode());
        logAddParam.setOperationTime(LocalDateTime.now());
        add(logAddParam);
    }

    @Override
    public void saveErrorLog(Context ctx) {
        if (StpUtil.isLogin()) {
            SysOperationLogAddDTO logAddParam = new SysOperationLogAddDTO();
            logAddParam.setIpAddress(ctx.realIp());
            logAddParam.setLogType(LogTypeEnum.OPERATION.getCode());
            logAddParam.setRequestWay(ctx.method());
            logAddParam.setLogNumber(System.currentTimeMillis());
            logAddParam.setOperator(String.valueOf(StpUtil.getLoginId()));
            logAddParam.setOperationStatus(OperationStatusEnum.ERROR.getCode());
            logAddParam.setOperationTime(LocalDateTime.now());
            try {
                add(logAddParam);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
