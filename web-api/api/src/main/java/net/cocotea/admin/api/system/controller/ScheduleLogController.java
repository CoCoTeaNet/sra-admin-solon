package net.cocotea.admin.api.system.controller;

import net.cocotea.admin.api.system.model.dto.ScheduleLogPageParam;
import net.cocotea.admin.api.system.model.vo.ScheduleLogVO;
import net.cocotea.admin.api.system.service.ScheduleLoggerService;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Controller
@Mapping("/schedule/jobLog")
public class ScheduleLogController {
    @Inject
    private ScheduleLoggerService scheduleJobLogService;

    @Post
    @Mapping("/deleteBatch")
    public ApiResult<?> deleteBatch(@Body List<String> param) throws BusinessException {
        boolean r = scheduleJobLogService.deleteBatch(param);
        return ApiResult.ok(r);
    }

    @Post
    @Mapping("/listByPage")
    public ApiResult<?> listByPage(@Body ScheduleLogPageParam param) throws BusinessException {
        Page<ScheduleLogVO> r = scheduleJobLogService.listByPage(param);
        return ApiResult.ok(r);
    }

}
