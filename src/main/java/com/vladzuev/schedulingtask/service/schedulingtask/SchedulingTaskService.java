package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.model.SchedulingConfiguration;
import com.vladzuev.schedulingtask.service.scheduledtask.ScheduledTask;
import com.vladzuev.schedulingtask.service.scheduledtaskexecutor.ScheduledTaskExecutor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.vladzuev.schedulingtask.util.CollectionUtil.collectToMap;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTask;
import static java.util.Date.from;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public final class SchedulingTaskService {
    private final Scheduler scheduler;
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskExecutor<?>> executorsByTaskTypes;

    public SchedulingTaskService(final Scheduler scheduler, final List<ScheduledTaskExecutor<?>> taskExecutors) {
        this.scheduler = scheduler;
        this.executorsByTaskTypes = collectToMap(taskExecutors, ScheduledTaskExecutor::getTaskType);
    }

    public void schedule(final ScheduledTask task) {
        try {
            final JobDetail jobDetail = this.createJobDetail(task);
            final Trigger trigger = createTrigger(task);
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (final SchedulerException cause) {
            throw new SchedulingTaskException(cause);
        }
    }

    private JobDetail createJobDetail(final ScheduledTask task) {
        final JobDetail detail = newJob(Job.class).build();
        putTask(detail, task);
        return detail;
    }

    private void putTaskExecutor(final ScheduledTask)

    private static Trigger createTrigger(final ScheduledTask<?> task) {
        final SchedulingConfiguration configuration = task.getConfiguration();
        final Date startDateTime = findStartDateTime(configuration);
        final ScheduleBuilder<SimpleTrigger> scheduleBuilder = createScheduleBuilder(configuration);
        return newTrigger()
                .startAt(startDateTime)
                .withSchedule(scheduleBuilder)
                .build();
    }

    private static Date findStartDateTime(final SchedulingConfiguration configuration) {
        final Instant startDateTime = configuration.getStartDateTime();
        return from(startDateTime);
    }

    private static ScheduleBuilder<SimpleTrigger> createScheduleBuilder(final SchedulingConfiguration configuration) {
        final int runIntervalInSecond = configuration.findRunIntervalInSecond();
        return simpleSchedule()
                .withIntervalInSeconds(runIntervalInSecond)
                .repeatForever();
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
