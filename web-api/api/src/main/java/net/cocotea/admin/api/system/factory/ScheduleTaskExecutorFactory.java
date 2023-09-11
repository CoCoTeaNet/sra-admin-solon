package net.cocotea.admin.api.system.factory;

import cn.hutool.core.util.ClassUtil;
import net.cocotea.admin.api.system.executor.ClzTaskExecutor;
import net.cocotea.admin.api.system.executor.FnTaskExecutor;
import net.cocotea.admin.api.system.executor.TaskExecutor;
import net.cocotea.admin.api.system.model.SraScheduler;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.common.model.BusinessException;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;

/**
 * @author Guo wentao
 * @project sss-rbac-admin
 * @date 2022-09-17 22:57:25
 */
@Component
public class ScheduleTaskExecutorFactory {
    public Object getJobInstance(Class<?> clazz) {
        try {
            return Solon.cfg().getBean(clazz);
        } catch (Exception ignore) {
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ignore) {
        }
        return null;
    }

    public TaskExecutor create(ScheduleTask scheduleTask, SraScheduler scheduler) throws BusinessException {
        String className = scheduleTask.getClassName();
        Class<?> executorClass;
        if ((executorClass = ClassUtil.loadClass(className)) == null) {
            throw new BusinessException("无法扫描到类信息，请检查类: [" + className + "]是否存在...");
        }
        Object instance = getJobInstance(executorClass);
        TaskExecutor taskExecutor;
        if (scheduleTask.getType() == 1) {
            taskExecutor = new FnTaskExecutor(scheduler, scheduleTask, instance);
        } else {
            taskExecutor = new ClzTaskExecutor(scheduler, scheduleTask, instance);
        }
        return taskExecutor;
    }
}
