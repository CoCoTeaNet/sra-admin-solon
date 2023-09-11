package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.model.dto.ScheduleLogAddParam;
import net.cocotea.admin.api.system.model.dto.ScheduleLogPageParam;
import net.cocotea.admin.api.system.model.dto.ScheduleLogUpdateParam;
import net.cocotea.admin.api.system.model.po.ScheduleLog;
import net.cocotea.admin.api.system.model.vo.ScheduleLogVO;
import net.cocotea.admin.api.system.service.ScheduleLoggerService;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo wentao
 * @project sss-rbac-admin
 * @date 2022-09-03 11:42:37
 */
@Service
public class ScheduleLogServiceImpl implements ScheduleLoggerService {

    @Inject
    private SqlToyLazyDao sqlToyLazyDao;

    @Override
    public boolean add(ScheduleLogAddParam param) throws BusinessException {
        ScheduleLog scheduleJob = Convert.convert(ScheduleLog.class, param);
        return sqlToyLazyDao.save(scheduleJob) != null;
    }

    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        List<ScheduleLog> scheduleJobs = new ArrayList<>(idList.size());
        for (String s : idList) {
            ScheduleLog ScheduleLog = new ScheduleLog();
            ScheduleLog.setId(s).setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
            scheduleJobs.add(ScheduleLog);
        }
        return sqlToyLazyDao.updateAll(scheduleJobs) > 0;
    }

    @Override
    public boolean update(ScheduleLogUpdateParam param) throws BusinessException {
        throw new BusinessException("不支持更新操作");
    }

    @Override
    public Page<ScheduleLogVO> listByPage(ScheduleLogPageParam param) throws BusinessException {
        if (param.getTriggerTimeRange() != null && !param.getTriggerTimeRange().isEmpty()) {
            param.setBeginTime(param.getTriggerTimeRange().get(0)).setEndTime(param.getTriggerTimeRange().get(1));
        }
        return sqlToyLazyDao.findPageBySql(param, "schedule_ScheduleLog_findByPageParam", param.getJobLog());
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        ScheduleLog ScheduleLog = new ScheduleLog()
                .setId(id)
                .setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
        return sqlToyLazyDao.update(ScheduleLog) > 0;
    }
}
