package com.vladzuev.schedulingtask.service.storage;

import com.vladzuev.schedulingtask.model.ScheduledTaskStatus;
import com.vladzuev.schedulingtask.model.task.ScheduledTask;

import java.util.Optional;

public interface ScheduledTaskStorage {
    ScheduledTask save(final ScheduledTask task);

    void updateStatus(final ScheduledTask task, final ScheduledTaskStatus status);

    void delete(final ScheduledTask task);

    Iterable<ScheduledTask> findAllActive();
}
