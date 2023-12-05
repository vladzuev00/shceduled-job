package com.vladzuev.schedulingtask.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTaskParams {
    private final User user;
    private final Instant startDateTime;
    private final ScheduledTaskRunInterval runInterval;

    public final int findRunIntervalInSecond() {
        return this.runInterval.findRunIntervalInSecond();
    }
}
