package com.vladzuev.job.service.scheduler;

import com.vladzuev.job.model.RepeatedScheduledTask;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Service
public final class RepeatedScheduledTaskScheduler extends ScheduledTaskScheduler<RepeatedScheduledTask> {

    public RepeatedScheduledTaskScheduler(final List<ScheduledTaskHandler<? extends RepeatedScheduledTask>> handlers) {
        super(handlers);
    }

    @Override
    protected SchedulingOperation createSchedulingOperation(final RepeatedScheduledTask task) {
        final long initialDelayMillis = task.findInitialDelayMillis();
        final long periodMillis = task.findRunIntervalInMillis();
        return (executorService, taskExecution) -> executorService.scheduleAtFixedRate(
                taskExecution,
                initialDelayMillis,
                periodMillis,
                MILLISECONDS
        );
    }

}
