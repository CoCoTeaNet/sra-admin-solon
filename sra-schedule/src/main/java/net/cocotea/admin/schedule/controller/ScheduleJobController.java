package net.cocotea.admin.schedule.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import net.cocotea.admin.schedule.param.ScheduleJobAddParam;
import net.cocotea.admin.schedule.param.ScheduleJobPageParam;
import net.cocotea.admin.schedule.vo.ScheduleJobVO;
import net.cocotea.admin.common.model.ApiResult;
import net.cocotea.admin.common.model.BusinessException;
import net.cocotea.admin.schedule.param.ScheduleJobUpdateParam;
import net.cocotea.admin.schedule.service.IScheduleJobService;
import org.noear.solon.annotation.*;
import org.sagacity.sqltoy.model.Page;

import java.util.List;

@Mapping("/schedule/job")
@Controller
public class ScheduleJobController {
    @Inject
    private IScheduleJobService scheduleJobService;

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/add")
    @Post
    public ApiResult<?> add(@Body ScheduleJobAddParam param) throws BusinessException {
        boolean r = scheduleJobService.add(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/deleteBatch")
    @Post
    public ApiResult<?> deleteBatch(@Body List<String> param) throws BusinessException {
        boolean r = scheduleJobService.deleteBatch(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/listByPage")
    @Post
    public ApiResult<?> listByPage(@Body ScheduleJobPageParam param) throws BusinessException {
        Page<ScheduleJobVO> r = scheduleJobService.listByPage(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/update")
    @Post
    public ApiResult<?> update(@Body ScheduleJobUpdateParam param) throws BusinessException {
        boolean r = scheduleJobService.update(param);
        return ApiResult.ok(r);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/execute")
    @Get
    public ApiResult<?> execute(String id) throws BusinessException {
        String uuid = scheduleJobService.execute(id);
        return ApiResult.ok(uuid);
    }

    @SaCheckRole(value = {"role:super:admin", "role:simple:admin"}, mode = SaMode.OR)
    @Mapping("/progress")
    @Get
    public ApiResult<?> progress(String id) throws BusinessException {
        boolean r = scheduleJobService.getExecuteProgress(id);
        return ApiResult.ok(r);
    }
}
