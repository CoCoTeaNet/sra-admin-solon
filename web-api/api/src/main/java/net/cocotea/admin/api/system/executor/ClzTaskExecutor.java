package net.cocotea.admin.api.system.executor;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import net.cocotea.admin.api.system.model.SraScheduler;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.common.model.BusinessException;

import java.util.Map;

/**
 * 类模式执行器
 *
 * @author Guo wentao
 * @project sss-rbac-admin
 * @date 2023-07-08 23:00:01
 */
public class ClzTaskExecutor extends TaskExecutor {

    protected Map<String, Object> executeParameters;

    public ClzTaskExecutor(SraScheduler scheduler, ScheduleTask scheduleTask, Object instance) throws BusinessException {
        super(scheduler, scheduleTask, instance);
    }

    @Override
    protected void doInitialize() throws BusinessException {
        String parameters = scheduleTask.getParameters();
        if (StrUtil.isNotBlank(parameters)) {
            executeParameters = JSONObject.parseObject(parameters);
        }
        if (executeParameters == null) {
            executeParameters = MapUtil.empty();
        }
    }

    @Override
    protected void doExecute() throws Exception {
        if (instance instanceof IBaseTaskExecutor) {
            ((IBaseTaskExecutor) instance).execute(executeParameters);
        }
    }
}
