package net.cocotea.admin.api.system.listener;

import net.cocotea.admin.api.system.executor.TaskExecutor;
import net.cocotea.admin.api.system.model.ScheduleTaskExecuteMeta;
import net.cocotea.admin.api.system.model.dto.ScheduleLogAddParam;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.api.system.service.ScheduleLoggerService;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Component
public class ScheduleLoggerListener implements ITaskListener {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleLoggerListener.class);
    private static final int EXEC_RESULT_SUCCESS = 1;
    private static final int EXEC_RESULT_FAILURE = 0;
    @Inject
    private ScheduleLoggerService scheduleLoggerService;

    @Override
    public void onStart(TaskExecutor taskExecutor) {
    }

    @Override
    public void onSucceeded(TaskExecutor taskExecutor) {
        try {
            onDone(taskExecutor.getScheduleTaskExecuteMeta(), true);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFailed(TaskExecutor taskExecutor, Throwable e) {
        try {
            onDone(taskExecutor.getScheduleTaskExecuteMeta(), false);
        } catch (BusinessException ex) {
            throw new RuntimeException(e);
        }
    }

    private void onDone(ScheduleTaskExecuteMeta meta, boolean isSuccess) throws BusinessException {
        Date triggerTime = meta.getTriggerTime();
        long taskTimeMillis = System.currentTimeMillis() - triggerTime.getTime();
        String operator = meta.getOperator();
        ScheduleTask job = meta.getTask();
        logger.info("计划任务: {} 执行耗时: {}ms", job.getName(), taskTimeMillis);
        ScheduleLogAddParam param = new ScheduleLogAddParam()
                .setJobId(job.getId())
                .setExeResult(isSuccess ? EXEC_RESULT_SUCCESS : EXEC_RESULT_FAILURE)
                .setTriggerBy(operator)
                .setTriggerTime(triggerTime)
                .setFinishTime(new Date())
                .setSpendTimeMillis(taskTimeMillis)
                .setCreateBy(operator)
                .setUpdateBy(operator);
        scheduleLoggerService.add(param);
    }
}
