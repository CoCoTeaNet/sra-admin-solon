package net.cocotea.admin.api.system.model;

import net.cocotea.admin.api.system.executor.TaskExecutor;
import net.cocotea.admin.api.system.model.po.ScheduleTask;

import java.util.Date;

public class ScheduleTaskExecuteMeta {

    private final ScheduleTask task;
    private final TaskExecutor executor;
    private String taskId;
    private Date triggerTime;
    private String operator;

    public ScheduleTaskExecuteMeta(ScheduleTask task, TaskExecutor executor) {
        this.task = task;
        this.executor = executor;
    }

    public ScheduleTask getTask() {
        return task;
    }

    public TaskExecutor getExecutor() {
        return executor;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
