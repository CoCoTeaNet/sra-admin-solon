package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.ScheduleTaskAddParam;
import net.cocotea.admin.api.system.model.dto.ScheduleTaskPageParam;
import net.cocotea.admin.api.system.model.dto.ScheduleTaskUpdateParam;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.api.system.model.vo.ScheduleTaskVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

public interface ScheduleTaskService extends IBaseService<Page<ScheduleTaskVO>, ScheduleTaskPageParam, ScheduleTaskAddParam, ScheduleTaskUpdateParam> {
}
