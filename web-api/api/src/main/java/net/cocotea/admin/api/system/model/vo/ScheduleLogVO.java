package net.cocotea.admin.api.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Guo wentao
 * @date 2022/9/1
 */
public class ScheduleLogVO implements Serializable {
//    private static final long serialVersionUID = -3378267153362384063L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 任务ID
     */
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务触发时间
     */
    private LocalDateTime triggerTime;

    /**
     * 任务触发人
     */
    private String triggerBy;

    /**
     * 任务执行结果;0失败 1成功
     */
    private Integer exeResult;

    /**
     * 任务执行耗时ms
     */
    private Long spendTimeMillis;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    public String getId() {
        return id;
    }

    public ScheduleLogVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getJobId() {
        return jobId;
    }

    public ScheduleLogVO setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public ScheduleLogVO setTriggerTime(LocalDateTime triggerTime) {
        this.triggerTime = triggerTime;
        return this;
    }

    public String getTriggerBy() {
        return triggerBy;
    }

    public ScheduleLogVO setTriggerBy(String triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    public Integer getExeResult() {
        return exeResult;
    }

    public ScheduleLogVO setExeResult(Integer exeResult) {
        this.exeResult = exeResult;
        return this;
    }

    public Long getSpendTimeMillis() {
        return spendTimeMillis;
    }

    public ScheduleLogVO setSpendTimeMillis(Long spendTimeMillis) {
        this.spendTimeMillis = spendTimeMillis;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ScheduleLogVO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ScheduleLogVO setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public ScheduleLogVO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public ScheduleLogVO setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public String getJobName() {
        return jobName;
    }

    public ScheduleLogVO setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }
}
