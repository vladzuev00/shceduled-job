package com.vladzuev.job.service.executor;

import com.vladzuev.job.model.ScheduledTask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
public abstract class ScheduledTaskScheduler<T extends ScheduledTask> {

    @Getter
    private final Class<T> taskType;

    public final ScheduledFuture<?> schedule(final ScheduledTask task, final ScheduledExecutorService executorService) {
        final T castedTask = this.taskType.cast(task);
        final Runnable taskExecution = () -> this.executeInternal(castedTask);
        final SchedulingOperation schedulingOperation = this.createSchedulingOperation(castedTask, executorService);
        return schedulingOperation.schedule(executorService, taskExecution);
    }

    protected abstract void executeInternal(final T task);

    protected abstract SchedulingOperation createSchedulingOperation(final T task,
                                                                     final ScheduledExecutorService executorService);

    @FunctionalInterface
    protected interface SchedulingOperation {
        ScheduledFuture<?> schedule(final ScheduledExecutorService executorService, final Runnable taskExecution);
    }
}
