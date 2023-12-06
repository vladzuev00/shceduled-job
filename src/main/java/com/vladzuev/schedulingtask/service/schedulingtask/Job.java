package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.service.schedulingtask.executor.ScheduledTaskExecutor;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTask;
import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTaskExecutor;

public final class Job implements org.quartz.Job {

    @Override
    public void execute(final JobExecutionContext context) {
        final JobDetail detail = context.getJobDetail();
        final ScheduledTask task = findTask(detail);
        final ScheduledTaskExecutor<?> executor = findTaskExecutor(detail);
        executor.execute(task);
    }
}
