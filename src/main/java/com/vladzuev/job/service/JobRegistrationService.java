package com.vladzuev.job.service;

import com.vladzuev.job.model.JobSetting;
import com.vladzuev.job.model.User;
import com.vladzuev.job.service.job.AbstractJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static java.util.Date.from;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@RequiredArgsConstructor
public final class JobRegistrationService {
    private final JobAttributeManager jobAttributeManager;
    private final Scheduler scheduler;

    public void register(final AbstractJob job, final JobSetting setting) {
        try {
            final JobDetail jobDetail = this.createJobDetail(job, setting);
            final Trigger trigger = createTrigger(setting);
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (final SchedulerException cause) {
            throw new JobRegistrationException(cause);
        }
    }

    private JobDetail createJobDetail(final AbstractJob job, final JobSetting setting) {
        final Class<? extends AbstractJob> jobType = job.getClass();
        final JobDetail jobDetail = newJob(jobType).build();
        this.putUserAttribute(jobDetail, setting);
        return jobDetail;
    }

    private void putUserAttribute(final JobDetail jobDetail, final JobSetting setting) {
        final User user = setting.getUser();
        this.jobAttributeManager.putUser(jobDetail, user);
    }

    private static Trigger createTrigger(final JobSetting setting) {
        final Date startDateTime = findStartDateTime(setting);
        final ScheduleBuilder<SimpleTrigger> scheduleBuilder = createScheduleBuilder(setting);
        return newTrigger()
                .startAt(startDateTime)
                .withSchedule(scheduleBuilder)
                .build();
    }

    private static Date findStartDateTime(final JobSetting setting) {
        final Instant startDateTime = setting.getStartDateTime();
        return from(startDateTime);
    }

    private static ScheduleBuilder<SimpleTrigger> createScheduleBuilder(final JobSetting setting) {
        final int runIntervalInSecond = setting.findRunIntervalInSecond();
        return simpleSchedule()
                .withIntervalInSeconds(runIntervalInSecond)
                .repeatForever();
    }

    private static final class JobRegistrationException extends RuntimeException {

        @SuppressWarnings("unused")
        public JobRegistrationException() {

        }

        @SuppressWarnings("unused")
        public JobRegistrationException(final String description) {
            super(description);
        }

        public JobRegistrationException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public JobRegistrationException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
