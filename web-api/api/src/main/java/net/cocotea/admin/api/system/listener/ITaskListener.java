package net.cocotea.admin.api.system.listener;


import net.cocotea.admin.api.system.executor.TaskExecutor;

public interface ITaskListener {

    void onStart(TaskExecutor taskExecutor);

    void onSucceeded(TaskExecutor taskExecutor);

    void onFailed(TaskExecutor taskExecutor, Throwable e);

}
