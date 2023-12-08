package com.vladzuev.job.service.scheduler;

import com.vladzuev.job.model.ScheduledTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static com.vladzuev.job.util.CollectionUtil.collectToMap;

public abstract class ScheduledTaskScheduler<T extends ScheduledTask> {
    private final Map<Class<? extends T>, ScheduledTaskHandler<? extends T>> taskHandlersByTypes;

    public ScheduledTaskScheduler(final List<ScheduledTaskHandler<? extends T>> handlers) {
        this.taskHandlersByTypes = collectToMap(handlers, ScheduledTaskHandler::getTaskType);
    }

    public final ScheduledFuture<?> schedule(final T task, final ScheduledExecutorService executorService) {
        final Runnable taskExecution = this.createTaskExecution(task);
        final SchedulingOperation schedulingOperation = this.createSchedulingOperation(task);
        return schedulingOperation.apply(executorService, taskExecution);
    }

    protected abstract SchedulingOperation createSchedulingOperation(final T task);

    private Runnable createTaskExecution(final ScheduledTask task) {
        final ScheduledTaskHandler<?> handler = this.findHandler(task);
        return () -> handler.handle(task);
    }

    private ScheduledTaskHandler<?> findHandler(final ScheduledTask task) {
        final Class<?> taskType = task.getClass();
        final ScheduledTaskHandler<?> handler = this.taskHandlersByTypes.get(taskType);
        if (handler == null) {
            throw new NoSuitableHandlerException("There is no handler for task: %s".formatted(task));
        }
        return handler;
    }

    @FunctionalInterface
    protected interface SchedulingOperation {
        ScheduledFuture<?> apply(final ScheduledExecutorService executorService, final Runnable taskExecution);
    }

    static final class NoSuitableHandlerException extends RuntimeException {

        @SuppressWarnings("unused")
        public NoSuitableHandlerException() {

        }

        public NoSuitableHandlerException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoSuitableHandlerException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuitableHandlerException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
