package com.vladzuev.schedulingtask.crud.dto.scheduledtask;

import com.vladzuev.schedulingtask.crud.dto.User;
import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class PrintingMessageScheduledTask extends RepeatedScheduledTask {
    private final String message;

    public PrintingMessageScheduledTask(final Long id,
                                        final Instant startDateTime,
                                        final User user,
                                        final ScheduledTaskRunInterval runInterval,
                                        final String message) {
        super(id, startDateTime, user, runInterval);
        this.message = message;
    }

}
