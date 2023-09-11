package net.cocotea.admin.api.system.executor;

import net.cocotea.admin.api.system.model.ScheduleTaskExecuteMeta;
import net.cocotea.admin.api.system.model.SraScheduler;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.common.model.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Guo wentao
 * @project sss-rbac-admin
 * @date 2023-07-08 22:50:47
 */
public abstract class TaskExecutor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);
    protected final SraScheduler scheduler;
    protected final ScheduleTask scheduleTask;
    protected final ScheduleTaskExecuteMeta scheduleTaskExecuteMeta;
    protected final Object instance;
    protected final Class<?> executorClass;
    protected boolean isRunning;

    public TaskExecutor(SraScheduler scheduler, ScheduleTask scheduleTask, Object instance) throws BusinessException {
        this.scheduleTaskExecuteMeta = new ScheduleTaskExecuteMeta(scheduleTask, this);
        this.scheduler = scheduler;
        this.scheduleTask = scheduleTask;
        this.instance = instance;
        this.executorClass = instance.getClass();
        doInitialize();
    }

    public void setup() {
        String taskId = this.scheduleTask.getId();
        this.scheduleTaskExecuteMeta.setTaskId(taskId);
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            scheduleTaskExecuteMeta.setTriggerTime(new Date());
            scheduler.asyncNotifyTaskStart(this);
            doExecute();
            scheduler.asyncNotifyTaskSucceeded(this);
        } catch (Exception e) {
            scheduler.asyncNotifyTaskFailed(this, e);
            logger.error("计划任务执行出现异常! ", e);
            throw new RuntimeException(e);
        } finally {
            isRunning = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ScheduleTaskExecuteMeta getScheduleTaskExecuteMeta() {
        return scheduleTaskExecuteMeta;
    }

    public SraScheduler getScheduler() {
        return scheduler;
    }

    public ScheduleTask getScheduleTask() {
        return scheduleTask;
    }

    public boolean isDisabledConcurrentExecute() {
        return scheduleTask.getConcurrentExec() == 0;
    }

    protected abstract void doInitialize() throws BusinessException;

    protected abstract void doExecute() throws Exception;

}
