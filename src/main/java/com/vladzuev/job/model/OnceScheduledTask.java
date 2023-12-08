package com.vladzuev.job.model;

import java.time.Instant;

public abstract class OnceScheduledTask extends ScheduledTask {

    public OnceScheduledTask(final Instant startDateTime) {
        super(startDateTime);
    }

}
