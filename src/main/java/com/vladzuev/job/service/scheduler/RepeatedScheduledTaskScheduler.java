package com.vladzuev.job.service.scheduler;

import com.vladzuev.job.model.RepeatedScheduledTask;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public final class RepeatedScheduledTaskScheduler extends ScheduledTaskScheduler<RepeatedScheduledTask> {

    public RepeatedScheduledTaskScheduler(final List<ScheduledTaskHandler<? extends RepeatedScheduledTask>> handlers) {
        super(handlers);
    }

    @Override
    protected SchedulingOperation createSchedulingOperation(final RepeatedScheduledTask task) {
        final long initialDelayMillis = task.findDelayMillis();
        final long periodMillis = task.findRunIntervalInMillis();
        return (executorService, taskExecution) -> executorService.scheduleAtFixedRate(
                taskExecution,
                initialDelayMillis,
                periodMillis,
                MILLISECONDS
        );
    }

}
