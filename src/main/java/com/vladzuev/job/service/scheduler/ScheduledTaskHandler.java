package com.vladzuev.job.service.scheduler;

import com.vladzuev.job.model.ScheduledTask;
import lombok.Getter;

public abstract class ScheduledTaskHandler<T extends ScheduledTask> {

    @Getter
    private final Class<T> taskType;

    public final void handle(final ScheduledTask task) {

    }
}
