package com.vladzuev.schedulingtask.model.task.oncerepeated;

import lombok.Getter;

import java.time.Instant;

@Getter
public final class OncePrintingMessageScheduledTask extends OnceRepeatedScheduledTask {
    private final String message;

    public OncePrintingMessageScheduledTask(final Instant startDateTime, final String message) {
        super(startDateTime);
        this.message = message;
    }

}
