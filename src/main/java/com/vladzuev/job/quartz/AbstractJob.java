package com.vladzuev.job.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public abstract class AbstractJob implements Job {

    @Override
    public final void execute(final JobExecutionContext context) {
//        newTrigger().withSchedule(simpleSchedule().)
        Scheduler a;
        a.unscheduleJob()
    }
}
