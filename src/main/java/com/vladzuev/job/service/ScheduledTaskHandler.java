package com.vladzuev.job.service;

import com.vladzuev.job.model.ScheduledTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ScheduledTaskHandler<T extends ScheduledTask> {
    private final Class<T> taskType;

    public final void handle(final ScheduledTask task) {
        final T concreteTask = this.taskType.cast(task);
        this.handleInternal(concreteTask);
    }

    protected abstract void handleInternal(final T task);
}
