package net.cocotea.admin.api.system.executor;

import java.util.Map;

/**
 * @author Guo wentao
 * @date 2022/9/1
 */
public interface IBaseTaskExecutor {

    /**
     * 计划任务执行方法
     *
     * @throws Exception 抛出的异常
     */
    void execute(Map<String, Object> param) throws Exception;

}
