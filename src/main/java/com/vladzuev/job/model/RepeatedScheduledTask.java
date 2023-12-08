package com.vladzuev.job.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class RepeatedScheduledTask extends ScheduledTask {
    private final ScheduledTaskRunInterval runInterval;

    public RepeatedScheduledTask(final Long id, final Instant startDateTime, final ScheduledTaskRunInterval runInterval) {
        super(id, startDateTime);
        this.runInterval = runInterval;
    }

    public final long findRunIntervalInMillis() {
        return this.runInterval.findRunIntervalInMillis();
    }
}
