package com.vladzuev.schedulingtask.crud.dto;

import com.vladzuev.schedulingtask.model.SchedulingConfiguration;
import com.vladzuev.schedulingtask.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class PrintingMessageScheduledTask extends ScheduledTask {
    private final String message;

    public PrintingMessageScheduledTask(final Long id,
                                        final SchedulingConfiguration configuration,
                                        final User user,
                                        final String message) {
        super(id, configuration, user);
        this.message = message;
    }

}
