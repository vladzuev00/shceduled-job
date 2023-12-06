package com.vladzuev.schedulingtask.model.task.oncerepeated;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.quartz.SimpleScheduleBuilder;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class OnceRepeatedScheduledTask extends ScheduledTask {

    public OnceRepeatedScheduledTask(final Long id, final Instant startDateTime) {
        super(id, startDateTime);
    }

    @Override
    protected final void configureScheduleBuilder(final SimpleScheduleBuilder builder) {
        builder.withRepeatCount(0);
    }
}
