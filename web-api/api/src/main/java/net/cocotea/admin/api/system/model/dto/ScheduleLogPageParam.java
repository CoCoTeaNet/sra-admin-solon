package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.ScheduleLogVO;
import org.sagacity.sqltoy.model.Page;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Guo wentao
 * @date 2022/8/29
 */
public class ScheduleLogPageParam extends Page<ScheduleLogVO> implements Serializable {

    private static final long serialVersionUID = -206977374259097534L;
    private ScheduleLogVO jobLog;
    private List<LocalDateTime> triggerTimeRange;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public ScheduleLogPageParam setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ScheduleLogPageParam setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<LocalDateTime> getTriggerTimeRange() {
        return triggerTimeRange;
    }

    public ScheduleLogPageParam setTriggerTimeRange(List<LocalDateTime> triggerTimeRange) {
        this.triggerTimeRange = triggerTimeRange;
        return this;
    }

    public ScheduleLogVO getJobLog() {
        return jobLog;
    }

    public ScheduleLogPageParam setJobLog(ScheduleLogVO jobLog) {
        this.jobLog = jobLog;
        return this;
    }
}
