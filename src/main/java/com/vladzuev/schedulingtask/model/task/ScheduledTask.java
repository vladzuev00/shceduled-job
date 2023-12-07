package com.vladzuev.schedulingtask.model.task;

import com.vladzuev.schedulingtask.model.ScheduledJob;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;

import java.time.Instant;
import java.util.Date;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTask;
import static java.util.Date.from;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class ScheduledTask {
    private final Long id;
    private final Instant startDateTime;

    public final Trigger createTrigger() {
        final Date startDateTime = from(this.startDateTime);
        final SimpleScheduleBuilder scheduleBuilder = this.createScheduleBuilder();
        return newTrigger()
                .startAt(startDateTime)
                .withSchedule(scheduleBuilder)
                .build();
    }

    public final JobKey createJobKey() {
        final String idAsString = this.id.toString();
        return new JobKey(idAsString);
    }

    public final JobDetail createJobDetail() {
        final JobKey jobKey = this.createJobKey();
        final JobDetail detail = newJob(ScheduledJob.class).withIdentity(jobKey).build();
        putTask(detail, this);
        return detail;
    }

    protected abstract void configureScheduleBuilder(final SimpleScheduleBuilder builder);

    private SimpleScheduleBuilder createScheduleBuilder() {
        final SimpleScheduleBuilder builder = simpleSchedule();
        this.configureScheduleBuilder(builder);
        return builder;
    }
}
