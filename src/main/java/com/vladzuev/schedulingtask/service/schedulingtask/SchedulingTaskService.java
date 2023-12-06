package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.crud.dto.scheduledtask.ScheduledTask;
import com.vladzuev.schedulingtask.service.schedulingtask.executor.ScheduledTaskExecutor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.vladzuev.schedulingtask.util.CollectionUtil.collectToMap;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTask;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.putTaskExecutor;
import static org.quartz.JobBuilder.newJob;

@Service
public final class SchedulingTaskService {
    private final Scheduler scheduler;
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskExecutor<?>> executorsByTaskTypes;

    public SchedulingTaskService(final Scheduler scheduler, final List<ScheduledTaskExecutor<?>> executors) {
        this.scheduler = scheduler;
        this.executorsByTaskTypes = collectToMap(executors, ScheduledTaskExecutor::getTaskType);
    }

    public void schedule(final ScheduledTask task) {
        try {
            final JobDetail detail = this.createJobDetail(task);
            final Trigger trigger = task.createTrigger();
            this.scheduler.scheduleJob(detail, trigger);
        } catch (final SchedulerException cause) {
            throw new SchedulingTaskException(cause);
        }
    }

    private JobDetail createJobDetail(final ScheduledTask task) {
        final JobDetail detail = newJob(Job.class).build();
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
            throw new SchedulingTaskException("There is no executor for task: %s".formatted(task));
        }
        return executor;
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
