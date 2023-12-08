package com.vladzuev.job.service;

import com.vladzuev.job.model.ScheduledTask;
import com.vladzuev.job.service.executor.ScheduledTaskScheduler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import static com.vladzuev.job.util.CollectionUtil.collectToMap;
import static java.util.concurrent.Executors.newScheduledThreadPool;

public final class ScheduledTaskManager {
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskScheduler<?>> taskSchedulersByTypes;
    private final ScheduledExecutorService executorService;

    public ScheduledTaskManager(final List<ScheduledTaskScheduler<?>> schedulers, final int threadCount) {
        this.taskSchedulersByTypes = collectToMap(schedulers, ScheduledTaskScheduler::getTaskType);
        this.executorService = newScheduledThreadPool(threadCount);
    }

    public void schedule(final ScheduledTask task) {
        final Class<? extends ScheduledTask> taskType = task.getClass();
        final ScheduledTaskScheduler<?> scheduler = this.taskSchedulersByTypes.get(taskType);
        final java.util.concurrent.ScheduledFuture<?> future = scheduler.schedule(task, this.executorService);
    }

    private static final class ScheduledTaskManagingException extends RuntimeException {

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException() {

        }

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException(final String description) {
            super(description);
        }

        public ScheduledTaskManagingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
