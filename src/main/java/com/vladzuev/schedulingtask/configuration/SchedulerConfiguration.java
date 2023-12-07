package com.vladzuev.schedulingtask.configuration;

import com.vladzuev.schedulingtask.service.factory.JobFactory;
import com.vladzuev.schedulingtask.service.listener.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;

//TODO: refactor
@Configuration
public class SchedulerConfiguration {

    @Bean
    public Scheduler scheduler(final JobListener jobListener, final JobFactory jobFactory)
            throws SchedulerException {
        Scheduler scheduler = getDefaultScheduler();
        scheduler.getListenerManager().addJobListener(jobListener);
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
        //TODO: call shutdown
        return scheduler;
    }
}
