package net.cocotea.admin.api.system.model;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.task.Task;
import net.cocotea.admin.api.system.executor.TaskExecutor;
import net.cocotea.admin.api.system.factory.ScheduleTaskExecutorFactory;
import net.cocotea.admin.api.system.listener.ITaskListener;
import net.cocotea.admin.api.system.model.po.ScheduleTask;
import net.cocotea.admin.common.model.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SraScheduler {
    private static final Logger logger = LoggerFactory.getLogger(SraScheduler.class);
    private static final String DEFAULT_THREAD_NAME = "SRA-Schedule-Thread";
    private static final String DEFAULT_CLEAR_EXECUTOR_TASK_CRON_EXP = "0/1 * * * * *";
    private final ConcurrentHashMap<String, TaskExecutor> temporaryExecutor = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, TaskExecutor> loadedTask = new ConcurrentHashMap<>(128);
    private final ThreadPoolExecutor executor;
    private final Scheduler scheduler = new Scheduler();
    private final ScheduleTaskExecutorFactory scheduleTaskExecutorFactory;
    private final List<ITaskListener> taskListeners = new LinkedList<>();

    public SraScheduler(ScheduleTaskExecutorFactory scheduleTaskExecutorFactory) {
        logger.info("初始化SraScheduler......");
        this.scheduleTaskExecutorFactory = scheduleTaskExecutorFactory;
        this.executor = new ThreadPoolExecutor(5, 5, 10,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10), runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName(DEFAULT_THREAD_NAME);
            thread.setDaemon(false);
            return thread;
        });
        scheduler.schedule(DEFAULT_CLEAR_EXECUTOR_TASK_CRON_EXP, new ClearTemporaryExecutorTask());
    }

    public SraScheduler(ThreadPoolExecutor executor, ScheduleTaskExecutorFactory scheduleTaskExecutorFactory) {
        this.executor = executor;
        this.scheduleTaskExecutorFactory = scheduleTaskExecutorFactory;
    }

    public void bootstrap() {
        scheduler.start();
    }

    public void shutdown() {
        scheduler.stop(true);
        taskListeners.clear();
        loadedTask.clear();
        temporaryExecutor.clear();
    }

    public void loadTasks(List<ScheduleTask> scheduleTasks) {
        for (ScheduleTask scheduleTask : scheduleTasks) {
            try {
                loadTask(scheduleTask);
            } catch (BusinessException e) {
                logger.info("任务: {} 加载失败，请检查原因！", scheduleTask.getClassName());
            }
        }
    }

    public void loadTask(ScheduleTask scheduleTask) throws BusinessException {
        String taskId = scheduleTask.getId();
        TaskExecutor taskExecutor = scheduleTaskExecutorFactory.create(scheduleTask, this);
        taskExecutor.setup();
        scheduler.schedule(taskId, scheduleTask.getCornExpression(), taskExecutor);
        loadedTask.put(taskId, taskExecutor);
    }

    public void unloadTasks(List<ScheduleTask> scheduleTasks) {
        for (ScheduleTask scheduleTask : scheduleTasks) {
            unloadTask(scheduleTask);
        }
    }

    public void unloadTask(ScheduleTask scheduleTask) {
        String taskId = scheduleTask.getId();
        scheduler.deschedule(taskId);
        loadedTask.remove(taskId);
    }

    public boolean isRunning(String taskId) {
        TaskExecutor executor = temporaryExecutor.getOrDefault(taskId, loadedTask.get(taskId));
        return executor != null && executor.isRunning();
    }

    public String run(ScheduleTask scheduleTask) throws BusinessException {
        String loginId = "";
        try {
            if (StpUtil.isLogin()) {
                loginId = (String) StpUtil.getLoginId();
            }
        } catch (Exception ignore) {
        }
        String taskId = IdUtil.fastSimpleUUID();
        TaskExecutor taskExecutor = scheduleTaskExecutorFactory.create(scheduleTask, this);
        ScheduleTaskExecuteMeta meta = taskExecutor.getScheduleTaskExecuteMeta();
        meta.setTaskId(taskId);
        meta.setOperator(loginId);
        temporaryExecutor.put(taskId, taskExecutor);
        return taskId;
    }

    public void stop(String taskId) {
        if (temporaryExecutor.containsKey(taskId)) {
            Runnable r = temporaryExecutor.remove(taskId);
            this.executor.remove(r);
        }
    }

    public void addTaskListener(ITaskListener taskListener) {
        taskListeners.add(taskListener);
    }

    public void removeTaskListener(ITaskListener taskListener) {
        Iterator<ITaskListener> iterator = taskListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == taskListener) {
                iterator.remove();
                break;
            }
        }
    }

    public void asyncNotifyTaskStart(TaskExecutor taskExecutor) {
        Iterator<ITaskListener> iterator = taskListeners.iterator();
        while (iterator.hasNext()) {
            executor.execute(() -> iterator.next().onStart(taskExecutor));
        }
    }


    public void asyncNotifyTaskSucceeded(TaskExecutor taskExecutor) {
        Iterator<ITaskListener> iterator = taskListeners.iterator();
        while (iterator.hasNext()) {
            executor.execute(() -> iterator.next().onSucceeded(taskExecutor));
        }
    }

    public void asyncNotifyTaskFailed(TaskExecutor taskExecutor, Throwable e) {
        Iterator<ITaskListener> iterator = taskListeners.iterator();
        while (iterator.hasNext()) {
            executor.execute(() -> iterator.next().onFailed(taskExecutor, e));
        }
    }

    private class ClearTemporaryExecutorTask implements Task {
        @Override
        public void execute() {
            Collection<TaskExecutor> executors = temporaryExecutor.values();
            executors.removeIf(taskExecutor -> !taskExecutor.isRunning());
        }
    }
}
