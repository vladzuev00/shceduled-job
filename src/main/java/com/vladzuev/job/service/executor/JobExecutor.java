package com.vladzuev.job.service.executor;

import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.model.JobPerformingStatus;
import com.vladzuev.job.crud.service.JobHistoryService;
import com.vladzuev.job.service.factory.JobHistoryFactory;
import com.vladzuev.job.service.handler.JobHandler;
import lombok.Getter;

import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newScheduledThreadPool;

public abstract class JobExecutor<J extends Job> {

    @Getter
    private final Class<J> jobType;

    private final ScheduledExecutorService executorService;
    private final JobHandler<J> jobHandler;

    public JobExecutor(final Class<J> jobType, final int handlingThreadCount, final JobHandler<J> jobHandler) {
        this.jobType = jobType;
        this.executorService = newScheduledThreadPool(handlingThreadCount);
        this.jobHandler = jobHandler;
    }

    public final void execute(final Collection<J> jobs) {
        this.executorService.s
    }
}
