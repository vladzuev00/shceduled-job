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
public abstract class RepeatedScheduledTask extends ScheduledTask {
    private final ScheduledTaskRunInterval runInterval;

    public RepeatedScheduledTask(final Long id,
                                 final Instant startDateTime,
                                 final User user,
                                 final ScheduledTaskRunInterval runInterval) {
        super(id, startDateTime, user);
        this.runInterval = runInterval;
    }

    public final int findRunIntervalInSecond() {
        return this.runInterval.findRunIntervalInSecond();
    }
}
