package com.vladzuev.job.model;

import java.time.Instant;

public abstract class OnceScheduledTask extends ScheduledTask {

    public OnceScheduledTask(final Long id, final Instant startDateTime) {
        super(id, startDateTime);
    }
}
