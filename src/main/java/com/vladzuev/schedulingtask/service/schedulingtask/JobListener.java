package com.vladzuev.schedulingtask.service.schedulingtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

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
        System.out.println("Job history should be inserted");
    }
}
