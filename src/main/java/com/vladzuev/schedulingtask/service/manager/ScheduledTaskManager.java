package com.vladzuev.schedulingtask.service.manager;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.service.storage.ScheduledTaskStorage;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Service;

import static com.vladzuev.schedulingtask.model.ScheduledTaskStatus.*;

@Service
@RequiredArgsConstructor
public final class ScheduledTaskManager {
    private final Scheduler scheduler;
    private final ScheduledTaskStorage taskStorage;

    public void run(final ScheduledTask task) {
        final ScheduledTask savedTask = this.taskStorage.save(task);
        this.validateId(savedTask);
        this.doAction(ScheduledTaskManager::scheduleTask, savedTask);
    }

    public void pause(final ScheduledTask task) {
        this.validateId(task);
        this.doActionByTaskJobKey(Scheduler::pauseJob, task);
        this.taskStorage.updateStatus(task, PAUSE);
    }

    //Если задача была на паузе, а потом возобновляется - будет все мгновенно все сделано, что не было сделано, пока задача была на паузе
    //Если задача не была запущена ранее или была удалена - ничего не произойдет
    public void resume(final ScheduledTask task) {
        this.validateId(task);
        this.doActionByTaskJobKey(Scheduler::resumeJob, task);
        this.taskStorage.updateStatus(task, ACTIVE);
    }

    public void archive(final ScheduledTask task) {
        this.validateId(task);
        this.doActionByTaskJobKey(Scheduler::deleteJob, task);
        this.taskStorage.updateStatus(task, ARCHIVE);
    }

    private void validateId(final ScheduledTask task) {
        if (task.getId() == null) {
            throw new ScheduledTaskManagingException("Managing task must have id");
        }
    }

    private static void scheduleTask(final Scheduler scheduler, final ScheduledTask task)
            throws SchedulerException {
        final JobDetail detail = task.createJobDetail();
        final Trigger trigger = task.createTrigger();
        scheduler.scheduleJob(detail, trigger);
    }

    private void doActionByTaskJobKey(final SchedulerAction<JobKey> action, final ScheduledTask task) {
        final JobKey jobKey = task.createJobKey();
        this.doAction(action, jobKey);
    }

    private <T> void doAction(final SchedulerAction<T> action, final T argument) {
        try {
            action.perform(this.scheduler, argument);
        } catch (final SchedulerException cause) {
            throw new ScheduledTaskManagingException(cause);
        }
    }

    @FunctionalInterface
    private interface SchedulerAction<T> {
        void perform(final Scheduler scheduler, final T argument) throws SchedulerException;
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
