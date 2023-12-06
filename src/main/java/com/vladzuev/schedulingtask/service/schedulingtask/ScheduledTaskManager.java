package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.model.Job;
import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.service.schedulingtask.executor.ScheduledTaskExecutor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.vladzuev.schedulingtask.util.CollectionUtil.collectToMap;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTask;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTaskExecutor;
import static org.quartz.JobBuilder.newJob;

@Service
public final class ScheduledTaskManager {
    private final Scheduler scheduler;
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskExecutor<?>> executorsByTaskTypes;

    public ScheduledTaskManager(final Scheduler scheduler, final List<ScheduledTaskExecutor<?>> executors) {
        this.scheduler = scheduler;
        this.executorsByTaskTypes = collectToMap(executors, ScheduledTaskExecutor::getTaskType);
    }

    public void schedule(final ScheduledTask task) {
        try {
            final JobDetail detail = this.createJobDetail(task);
            final Trigger trigger = task.createTrigger();
            this.scheduler.scheduleJob(detail, trigger);
        } catch (final SchedulerException cause) {
            throw new ScheduledTaskManagingException(cause);
        }
    }

    public void pause(final ScheduledTask task) {
        try {
            final JobKey jobKey = createJobKey(task);
            this.scheduler.pauseJob(jobKey);
        } catch (final SchedulerException cause) {
            throw new ScheduledTaskManagingException(cause);
        }
    }



    private JobDetail createJobDetail(final ScheduledTask task) {
        final JobKey jobKey = createJobKey(task);
        final JobDetail detail = newJob(Job.class).withIdentity(jobKey).build();
        putTask(detail, task);
        this.putExecutor(detail, task);
        return detail;
    }

    private void putExecutor(final JobDetail detail, final ScheduledTask task) {
        final ScheduledTaskExecutor<?> executor = this.findExecutor(task);
        putTaskExecutor(detail, executor);
    }

    private ScheduledTaskExecutor<?> findExecutor(final ScheduledTask task) {
        final Class<? extends ScheduledTask> taskType = task.getClass();
        final ScheduledTaskExecutor<?> executor = this.executorsByTaskTypes.get(taskType);
        if (executor == null) {
            throw new ScheduledTaskManagingException("There is no executor for task: %s".formatted(task));
        }
        return executor;
    }

    private static JobKey createJobKey(final ScheduledTask task) {
        final Long id = task.getId();
        final String idAsString = id.toString();
        return new JobKey(idAsString);
    }

    private static final class ScheduledTaskManagingException extends RuntimeException {

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException() {

        }

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException(final String description) {
            super(description);
        }

        public ScheduledTaskManagingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public ScheduledTaskManagingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
