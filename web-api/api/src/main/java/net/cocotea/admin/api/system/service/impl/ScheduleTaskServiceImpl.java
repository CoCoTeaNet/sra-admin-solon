package net.cocotea.admin.api.system.service.impl;

import cn.hutool.core.convert.Convert;
import net.cocotea.admin.api.system.factory.ScheduleTaskExecutorFactory;
import net.cocotea.admin.api.system.listener.ScheduleLoggerListener;
import net.cocotea.admin.api.system.model.SraScheduler;
import net.cocotea.admin.api.system.model.dto.ScheduleTaskAddParam;
import net.cocotea.admin.api.system.model.dto.ScheduleTaskPageParam;
import net.cocotea.admin.api.system.model.dto.ScheduleTaskUpdateParam;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.api.system.model.vo.ScheduleTaskVO;
import net.cocotea.admin.api.system.service.ScheduleTaskService;
import net.cocotea.admin.common.enums.ActiveEnum;
import net.cocotea.admin.common.enums.DeleteStatusEnum;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.core.bean.InitializingBean;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.springframework.scheduling.support.CronExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService, InitializingBean {
    @Inject
    private SqlToyLazyDao sqlToyLazyDao;
    @Inject
    private ScheduleLoggerListener scheduleLoggerListener;
    @Inject
    private ScheduleTaskExecutorFactory scheduleTaskExecutorFactory;
    private SraScheduler scheduler;

    @Override
    public void afterInjection() throws Throwable {
        this.scheduler = new SraScheduler(scheduleTaskExecutorFactory);
        this.scheduler.addTaskListener(scheduleLoggerListener);
    }

    @Override
    public boolean add(ScheduleTaskAddParam param) throws BusinessException {
        ScheduleTask scheduleTask = Convert.convert(ScheduleTask.class, param);
        if (!CronExpression.isValidExpression(scheduleTask.getCornExpression())) {
            throw new BusinessException("非法Cron表达式，请检查表达式格式");
        }
        Object save = sqlToyLazyDao.save(scheduleTask);
        try {
            scheduler.loadTask(scheduleTask);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return save != null;
    }

    @Override
    public boolean deleteBatch(List<String> idList) throws BusinessException {
        List<ScheduleTask> scheduleJobs = new ArrayList<>(idList.size());
        for (String s : idList) {
            ScheduleTask scheduleJob = new ScheduleTask();
            scheduleJob.setId(s).setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
            scheduleJobs.add(scheduleJob);
        }
        return sqlToyLazyDao.updateAll(scheduleJobs) > 0;
    }

    @Override
    public boolean update(ScheduleTaskUpdateParam param) throws BusinessException {
        ScheduleTask scheduleTask = Convert.convert(ScheduleTask.class, param);
        if (!CronExpression.isValidExpression(scheduleTask.getCornExpression())) {
            throw new BusinessException("非法Cron表达式，请检查表达式格式");
        }
        Long row = sqlToyLazyDao.update(scheduleTask);
        if (row > 0) {
            try {
                if (Objects.equals(scheduleTask.getActive(), ActiveEnum.ACTIVE.getCode())) {
                    scheduler.loadTask(scheduleTask);
                } else {
                    scheduler.unloadTask(scheduleTask);
                }
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
            return true;
        }
        return false;
    }

    @Override
    public Page<ScheduleTaskVO> listByPage(ScheduleTaskPageParam param) throws BusinessException {
        return sqlToyLazyDao.findPageBySql(param, "schedule_scheduleJob_findByPageParam", param.getJob());
    }

    @Override
    public boolean delete(String id) throws BusinessException {
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setId(id).setDeleteStatus(DeleteStatusEnum.DELETE.getCode());
        Long row = sqlToyLazyDao.update(scheduleTask);
        if (row > 0) {
            try {
                scheduler.unloadTask(scheduleTask);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
            return true;
        }
        return false;
    }

    public List<ScheduleTask> getAllActiveScheduleTask() throws BusinessException {
        ScheduleTask scheduleJob = new ScheduleTask().setActive(ActiveEnum.ACTIVE.getCode());
        return sqlToyLazyDao.findBySql("schedule_scheduleJob_findByEntityParam", scheduleJob);
    }

    public String execute(String id) throws BusinessException {
        ScheduleTask scheduleTask = new ScheduleTask().setId(id);
        ScheduleTask task = sqlToyLazyDao.load(scheduleTask);
        try {
            return scheduler.run(sqlToyLazyDao.load(task));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public boolean getExecuteProgress(String uuid) throws BusinessException {
        return !scheduler.isRunning(uuid);
    }
}
