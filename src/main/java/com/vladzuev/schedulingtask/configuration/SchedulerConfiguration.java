package com.vladzuev.schedulingtask.configuration;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;

//TODO: refactor
@Configuration
public class SchedulerConfiguration {

    @Bean
    public Scheduler scheduler()
            throws SchedulerException {
        Scheduler scheduler = getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }
}
