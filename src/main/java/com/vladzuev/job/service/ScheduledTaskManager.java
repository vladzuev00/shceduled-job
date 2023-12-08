package com.vladzuev.job.service;

import com.vladzuev.job.model.OnceScheduledTask;
import com.vladzuev.job.model.RepeatedScheduledTask;
import com.vladzuev.job.model.ScheduledTask;
import com.vladzuev.job.service.scheduler.OnceScheduledTaskScheduler;
import com.vladzuev.job.service.scheduler.RepeatedScheduledTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Service
public final class ScheduledTaskManager {
    private final RepeatedScheduledTaskScheduler repeatedTaskScheduler;
    private final OnceScheduledTaskScheduler onceTaskScheduler;
    private final ScheduledFutureStore futureStore;
    private final ScheduledExecutorService executorService;

    public ScheduledTaskManager(final RepeatedScheduledTaskScheduler repeatedTaskScheduler,
                                final OnceScheduledTaskScheduler onceTaskScheduler,
                                final ScheduledFutureStore futureStore,
                                final int threadCount) {
        this.repeatedTaskScheduler = repeatedTaskScheduler;
        this.onceTaskScheduler = onceTaskScheduler;
        this.futureStore = futureStore;
        this.executorService = newScheduledThreadPool(threadCount);
    }

    public void schedule(final OnceScheduledTask task) {
        final ScheduledFuture<?> future = this.onceTaskScheduler.schedule(task, this.executorService);
        final Long taskId = task.getId();
        this.futureStore.put(future, taskId);
    }

    public void schedule(final RepeatedScheduledTask task) {
        final ScheduledFuture<?> future = this.repeatedTaskScheduler.schedule(task, this.executorService);
        final Long taskId = task.getId();
        this.futureStore.put(future, taskId);
    }

    public void pause(final ScheduledTask task) {
        final Long taskId = task.getId();
        final java.util.Optional<ScheduledFuture<?>> future = this.futureStore.removeAndGet(taskId);
    }
}
