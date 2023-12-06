package com.vladzuev.schedulingtask.model.task.foreverrepeated;

import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class ForeverPrintingMessageScheduledTask extends ForeverRepeatedScheduledTask {
    private final String message;

    public ForeverPrintingMessageScheduledTask(final Instant startDateTime,
                                               final ScheduledTaskRunInterval runInterval,
                                               final String message) {
        super(startDateTime, runInterval);
        this.message = message;
    }

}
