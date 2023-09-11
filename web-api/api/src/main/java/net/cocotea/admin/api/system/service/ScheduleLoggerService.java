package net.cocotea.admin.api.system.service;

import net.cocotea.admin.api.system.model.dto.ScheduleLogAddParam;
import net.cocotea.admin.api.system.model.dto.ScheduleLogPageParam;
import net.cocotea.admin.api.system.model.dto.ScheduleLogUpdateParam;
import net.cocotea.admin.api.system.model.vo.ScheduleLogVO;
import net.cocotea.admin.common.service.IBaseService;
import org.sagacity.sqltoy.model.Page;

/**
 * @author Guo wentao
 * @date 2022/9/1
 */
public interface ScheduleLoggerService extends IBaseService<Page<ScheduleLogVO>, ScheduleLogPageParam, ScheduleLogAddParam, ScheduleLogUpdateParam> {
}
