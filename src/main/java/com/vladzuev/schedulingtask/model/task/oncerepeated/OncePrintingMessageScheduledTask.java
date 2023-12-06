package com.vladzuev.schedulingtask.model.task.oncerepeated;

import com.vladzuev.schedulingtask.crud.dto.User;
import lombok.Getter;

import java.time.Instant;

@Getter
public final class OncePrintingMessageScheduledTask extends OnceRepeatedScheduledTask {
    private final String message;

    public OncePrintingMessageScheduledTask(final Long id,
                                            final Instant startDateTime,
                                            final User user,
                                            final String message) {
        super(id, startDateTime, user);
        this.message = message;
    }

}
