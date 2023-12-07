package com.vladzuev.schedulingtask.service.executor;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTaskExecutor<T extends ScheduledTask> {
    private final Class<T> taskType;

    public final void execute(final ScheduledTask task) {
        final T concreteTask = this.taskType.cast(task);
        this.executeConcreteTask(concreteTask);
    }

    protected abstract void executeConcreteTask(final T task);
}
