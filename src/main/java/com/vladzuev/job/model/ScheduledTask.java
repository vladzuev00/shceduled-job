package com.vladzuev.job.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

import static java.lang.System.currentTimeMillis;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTask {
    private final Instant startDateTime;

    public final long findDelayMillis() {
        return this.startDateTime.toEpochMilli() - currentTimeMillis();
    }
}
