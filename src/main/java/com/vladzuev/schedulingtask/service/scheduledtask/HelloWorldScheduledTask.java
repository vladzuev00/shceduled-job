package com.vladzuev.schedulingtask.service.scheduledtask;

import com.vladzuev.schedulingtask.crud.dto.ScheduledTask;
import com.vladzuev.schedulingtask.model.SchedulingConfiguration;

import static java.lang.System.out;

public final class HelloWorldScheduledTask extends ScheduledTask<ScheduledTaskParams> {

    public HelloWorldScheduledTask(final SchedulingConfiguration configuration, final ScheduledTaskParams params) {
        super(configuration, params);
    }

    @Override
    protected void execute(final ScheduledTaskParams params) {
        out.println("Hello. Your params: " + params);
    }
}
