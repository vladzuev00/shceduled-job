package com.vladzuev.job.quartz.configuration;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;

@Configuration
public class SchedulerConfiguration {

    @Bean(initMethod = "startScheduler", destroyMethod = "shutdownScheduler")
    public Scheduler scheduler()
            throws SchedulerException {
        return getDefaultScheduler();
    }
}
