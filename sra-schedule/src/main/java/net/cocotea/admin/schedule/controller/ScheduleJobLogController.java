package net.cocotea.admin.schedule.controller;

import net.cocotea.admin.schedule.param.ScheduleJobLogPageParam;
import net.cocotea.admin.schedule.service.IScheduleJobLogService;
import net.cocotea.admin.schedule.vo.ScheduleJobLogVO;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Mapping("/schedule/jobLog")
@Controller
public class ScheduleJobLogController {
    @Inject
    private IScheduleJobLogService scheduleJobLogService;


    @Mapping("/deleteBatch")
    @Post
    public ApiResult<?> deleteBatch(@Body List<String> param) throws BusinessException {
        boolean r = scheduleJobLogService.deleteBatch(param);
        return ApiResult.ok(r);
    }

    @Mapping("/listByPage")
    @Post
    public ApiResult<?> listByPage(@Body ScheduleJobLogPageParam param) throws BusinessException {
        Page<ScheduleJobLogVO> r = scheduleJobLogService.listByPage(param);
        return ApiResult.ok(r);
    }

}
