package net.cocotea.admin.api.system.model.dto;

import net.cocotea.admin.api.system.model.vo.ScheduleTaskVO;
import org.sagacity.sqltoy.model.Page;

/**
 * CoCoTea
 *
 * @date 2022-9-2 20:49:35
 */
public class ScheduleTaskPageParam extends Page<ScheduleTaskVO> {
    private ScheduleTaskVO job;

    public ScheduleTaskVO getJob() {
        return job;
    }

    public ScheduleTaskPageParam setJob(ScheduleTaskVO job) {
        this.job = job;
        return this;
    }
}
