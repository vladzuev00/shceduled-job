package com.vladzuev.schedulingtask.service.scheduledtaskexecutor;

import com.vladzuev.schedulingtask.crud.dto.scheduledtask.ScheduledTask;
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
