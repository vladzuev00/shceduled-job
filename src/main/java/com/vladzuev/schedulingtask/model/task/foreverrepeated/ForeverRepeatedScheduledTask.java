package com.vladzuev.schedulingtask.model.task.foreverrepeated;

import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.quartz.SimpleScheduleBuilder;

import java.time.Instant;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class ForeverRepeatedScheduledTask extends ScheduledTask {
    private final ScheduledTaskRunInterval runInterval;

    public ForeverRepeatedScheduledTask(final Long id,
                                        final Instant startDateTime,
                                        final ScheduledTaskRunInterval runInterval) {
        super(id, startDateTime);
        this.runInterval = runInterval;
    }

    @Override
    protected final void configureScheduleBuilder(final SimpleScheduleBuilder builder) {
        final int runIntervalInSeconds = this.runInterval.findRunIntervalInSecond();
        builder.withIntervalInSeconds(runIntervalInSeconds).repeatForever();
    }
}
