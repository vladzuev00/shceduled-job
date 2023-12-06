package com.vladzuev.schedulingtask.service.schedulingtask;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import static com.vladzuev.schedulingtask.util.JobDetailUtil.findTask;
import static java.lang.System.out;

@Component
public final class JobListener implements org.quartz.JobListener {
    private static final String NAME = "MAIN_JOB_LISTER";

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
        if (exception == null) {
            out.printf("Job history should be inserted for success task '%s'\n", task);
        } else {
            out.printf("Job history should be inserted for failed task '%s'\n", task);
        }
    }
}
