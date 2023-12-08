package com.vladzuev.job.service.scheduler;

import com.vladzuev.job.model.OnceScheduledTask;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Service
public final class OnceScheduledTaskScheduler extends ScheduledTaskScheduler<OnceScheduledTask> {

    public OnceScheduledTaskScheduler(final List<ScheduledTaskHandler<? extends OnceScheduledTask>> handlers) {
        super(handlers);
    }

    @Override
    protected SchedulingOperation createSchedulingOperation(final OnceScheduledTask task) {
        final long delayMillis = task.findDelayMillis();
        return (executorService, taskExecution) -> executorService.schedule(taskExecution, delayMillis, MILLISECONDS);
    }
}
