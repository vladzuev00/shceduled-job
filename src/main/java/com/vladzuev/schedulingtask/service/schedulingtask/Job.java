package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.service.jobtask.ScheduledTask;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTask;

public final class Job implements org.quartz.Job {

    @Override
    public void execute(final JobExecutionContext context) {
        final JobDetail detail = context.getJobDetail();
        final ScheduledTask<?> task = findTask(detail);
        task.execute();
    }
}
