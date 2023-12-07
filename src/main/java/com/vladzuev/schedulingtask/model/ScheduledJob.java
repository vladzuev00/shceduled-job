package com.vladzuev.schedulingtask.model;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.service.executor.ScheduledTaskExecutor;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

import java.util.Map;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTask;

@RequiredArgsConstructor
public final class ScheduledJob implements Job {
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskExecutor<?>> executorsByTaskTypes;

    @Override
    public void execute(final JobExecutionContext context) {
        final JobDetail detail = context.getJobDetail();
        final ScheduledTask task = findTask(detail);
        final ScheduledTaskExecutor<?> executor = this.findExecutor(task);
        executor.execute(task);
    }

    private ScheduledTaskExecutor<?> findExecutor(final ScheduledTask task) {
        final Class<? extends ScheduledTask> taskType = task.getClass();
        final ScheduledTaskExecutor<?> executor = this.executorsByTaskTypes.get(taskType);
        if (executor == null) {
            throw new NoSuitableExecutorException("There is no executor for task: %s".formatted(task));
        }
        return executor;
    }

    static final class NoSuitableExecutorException extends RuntimeException {

        @SuppressWarnings("unused")
        public NoSuitableExecutorException() {

        }

        public NoSuitableExecutorException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoSuitableExecutorException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuitableExecutorException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
