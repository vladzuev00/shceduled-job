package com.vladzuev.schedulingtask.service;

import com.vladzuev.schedulingtask.model.ScheduledTaskParams;
import com.vladzuev.schedulingtask.service.jobtask.ScheduledTask;
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
public final class SchedulingTaskService {
    private static final String ATTRIBUTE_NAME_TASK = "task";

    private final Scheduler scheduler;

    public void schedule(final ScheduledTask<?> task) {
        try {
            final JobDetail jobDetail = this.createJobDetail(task);
            final Trigger trigger = createTrigger(task);
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (final SchedulerException cause) {
            throw new SchedulingTaskException(cause);
        }
    }

    private JobDetail createJobDetail(final ScheduledTask<?> task) {
        final JobDetail detail = newJob(Job.class).build();
        putTask(detail, task);
        return detail;
    }

    private static Trigger createTrigger(final ScheduledTask<?> task) {
        final ScheduledTaskParams params = task.getParams();
        final Date startDateTime = findStartDateTime(params);
        final ScheduleBuilder<SimpleTrigger> scheduleBuilder = createScheduleBuilder(params);
        return newTrigger()
                .startAt(startDateTime)
                .withSchedule(scheduleBuilder)
                .build();
    }

    private static Date findStartDateTime(final ScheduledTaskParams params) {
        final Instant startDateTime = params.getStartDateTime();
        return from(startDateTime);
    }

    private static ScheduleBuilder<SimpleTrigger> createScheduleBuilder(final ScheduledTaskParams params) {
        final int runIntervalInSecond = params.findRunIntervalInSecond();
        return simpleSchedule()
                .withIntervalInSeconds(runIntervalInSecond)
                .repeatForever();
    }

    private static void putTask(final JobDetail detail, final ScheduledTask<?> task) {
        detail.getJobDataMap().put(ATTRIBUTE_NAME_TASK, task);
    }

    private static final class Job implements org.quartz.Job {

        @Override
        public void execute(final JobExecutionContext context) {
            final JobDetail detail = context.getJobDetail();
            final ScheduledTask<?> task = findTask(detail);
            task.execute();
        }

        private static ScheduledTask<?> findTask(final JobDetail detail) {
            return (ScheduledTask<?>) detail.getJobDataMap().get(ATTRIBUTE_NAME_TASK);
        }
    }

    private static final class SchedulingTaskException extends RuntimeException {

        @SuppressWarnings("unused")
        public SchedulingTaskException() {

        }

        @SuppressWarnings("unused")
        public SchedulingTaskException(final String description) {
            super(description);
        }

        public SchedulingTaskException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public SchedulingTaskException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
