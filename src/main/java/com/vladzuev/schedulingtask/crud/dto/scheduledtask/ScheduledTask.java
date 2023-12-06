package com.vladzuev.schedulingtask.crud.dto.scheduledtask;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.schedulingtask.crud.dto.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;

import java.time.Instant;
import java.util.Date;

import static java.util.Date.from;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class ScheduledTask implements AbstractDto<Long> {
    private final Long id;
    private final Instant startDateTime;
    private final User user;

    public final Trigger createTrigger() {
        final Date startDateTime = from(this.startDateTime);
        final SimpleScheduleBuilder scheduleBuilder = this.createScheduleBuilder();
        return newTrigger()
                .startAt(startDateTime)
                .withSchedule(scheduleBuilder)
                .build();
    }

    protected abstract void configureScheduleBuilder(final SimpleScheduleBuilder builder);

    private SimpleScheduleBuilder createScheduleBuilder() {
        final SimpleScheduleBuilder builder = simpleSchedule();
        this.configureScheduleBuilder(builder);
        return builder;
    }
}
