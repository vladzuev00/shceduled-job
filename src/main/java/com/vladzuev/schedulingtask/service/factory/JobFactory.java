package com.vladzuev.schedulingtask.service.factory;

import com.vladzuev.schedulingtask.model.ScheduledJob;
import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.service.executor.ScheduledTaskExecutor;
import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.vladzuev.schedulingtask.util.CollectionUtil.collectToMap;

@Component
public final class JobFactory implements org.quartz.spi.JobFactory {
    private final Map<Class<? extends ScheduledTask>, ScheduledTaskExecutor<?>> executorsByTaskTypes;

    public JobFactory(final List<ScheduledTaskExecutor<?>> executors) {
        this.executorsByTaskTypes = collectToMap(executors, ScheduledTaskExecutor::getTaskType);
    }

    @Override
    public ScheduledJob newJob(final TriggerFiredBundle bundle, final Scheduler scheduler) {
        return new ScheduledJob(this.executorsByTaskTypes);
    }
}
