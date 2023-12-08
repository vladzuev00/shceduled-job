package com.vladzuev.job.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import static java.util.Optional.ofNullable;

@Component
public final class ScheduledFutureStore {
    private final Map<Long, ScheduledFuture<?>> taskFuturesByTaskIds = new ConcurrentHashMap<>();

    public void put(final ScheduledFuture<?> future, final Long taskId) {
        this.taskFuturesByTaskIds.put(taskId, future);
    }

    public Optional<ScheduledFuture<?>> removeAndGet(final Long taskId) {
        final ScheduledFuture<?> future = this.taskFuturesByTaskIds.remove(taskId);
        return ofNullable(future);
    }
}
