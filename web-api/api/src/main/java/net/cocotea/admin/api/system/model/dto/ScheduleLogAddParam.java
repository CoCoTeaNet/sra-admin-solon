package net.cocotea.admin.api.system.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Guo wentao
 * @date 2022/8/29
 */
public class ScheduleLogAddParam implements Serializable {
    private static final long serialVersionUID = -1166568456984487894L;

    private String jobId;
    private String triggerBy;
    private Date triggerTime;
    private Date finishTime;
    private long spendTimeMillis;
    private Integer exeResult;
    private String createBy;
    private String updateBy;


    public String getJobId() {
        return jobId;
    }

    public ScheduleLogAddParam setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public ScheduleLogAddParam setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
        return this;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public ScheduleLogAddParam setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public Integer getExeResult() {
        return exeResult;
    }

    public ScheduleLogAddParam setExeResult(Integer exeResult) {
        this.exeResult = exeResult;
        return this;
    }

    public long getSpendTimeMillis() {
        return spendTimeMillis;
    }

    public ScheduleLogAddParam setSpendTimeMillis(long spendTimeMillis) {
        this.spendTimeMillis = spendTimeMillis;
        return this;
    }

    public String getTriggerBy() {
        return triggerBy;
    }

    public ScheduleLogAddParam setTriggerBy(String triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ScheduleLogAddParam setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public ScheduleLogAddParam setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleJobLogAddParam{" +
                "jobId='" + jobId + '\'' +
                ", triggerBy='" + triggerBy + '\'' +
                ", triggerTime=" + triggerTime +
                ", finishTime=" + finishTime +
                ", spendTimeMillis=" + spendTimeMillis +
                ", exeResult=" + exeResult +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                '}';
    }
}
