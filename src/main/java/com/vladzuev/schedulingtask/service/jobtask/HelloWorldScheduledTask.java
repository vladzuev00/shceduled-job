package com.vladzuev.schedulingtask.service.jobtask;

import com.vladzuev.schedulingtask.model.ScheduledTaskParams;

public final class HelloWorldScheduledTask extends ScheduledTask<ScheduledTaskParams> {

    public HelloWorldScheduledTask(final ScheduledTaskParams params) {
        super(params);
    }

    @Override
    protected void execute(final ScheduledTaskParams params) {
        System.out.println("Hello. Your params: " + params);
    }
}
