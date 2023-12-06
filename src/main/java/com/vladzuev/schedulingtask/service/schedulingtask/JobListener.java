package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.crud.dto.scheduledtask.ScheduledTask;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTask;
import static java.lang.System.out;

@Component
public final class JobListener implements org.quartz.JobListener {
    private static final String NAME = "JOB_LISTENER";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void jobToBeExecuted(final JobExecutionContext context) {

    }

    @Override
    public void jobExecutionVetoed(final JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(final JobExecutionContext context, final JobExecutionException exception) {
        final JobDetail jobDetail = context.getJobDetail();
        final ScheduledTask task = findTask(jobDetail);
        out.printf("Job history should be inserted for task '%s'\n", task);
    }
}
