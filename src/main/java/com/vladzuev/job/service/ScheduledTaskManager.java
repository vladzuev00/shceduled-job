package com.vladzuev.job.service;

import com.vladzuev.job.model.OnceScheduledTask;
import com.vladzuev.job.model.RepeatedScheduledTask;
import com.vladzuev.job.service.scheduler.OnceScheduledTaskScheduler;
import com.vladzuev.job.service.scheduler.RepeatedScheduledTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Service
public final class ScheduledTaskManager {
    private final RepeatedScheduledTaskScheduler repeatedTaskScheduler;
    private final OnceScheduledTaskScheduler onceTaskScheduler;
    private final ScheduledExecutorService executorService;

    public ScheduledTaskManager(final RepeatedScheduledTaskScheduler repeatedTaskScheduler,
                                final OnceScheduledTaskScheduler onceTaskScheduler,
                                final int threadCount) {
        this.repeatedTaskScheduler = repeatedTaskScheduler;
        this.onceTaskScheduler = onceTaskScheduler;
        this.executorService = newScheduledThreadPool(threadCount);
    }

    public void schedule(final OnceScheduledTask task) {
        this.onceTaskScheduler.schedule(task, this.executorService);
    }

    public void schedule(final RepeatedScheduledTask task) {
        this.repeatedTaskScheduler.schedule(task, this.executorService);
    }
}
