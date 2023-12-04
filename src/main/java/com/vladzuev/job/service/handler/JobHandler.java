package com.vladzuev.job.service.handler;

import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.model.JobPerformingStatus;
import com.vladzuev.job.crud.service.JobHistoryService;
import com.vladzuev.job.service.factory.JobHistoryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class JobHandler<J extends Job> {
    private final JobHistoryFactory historyFactory;
    private final JobHistoryService historyService;

    public final void handle(final J job) {
        final JobPerformingStatus performingStatus = this.perform(job);
        final JobHistory history = this.historyFactory.create(job, performingStatus);
        this.historyService.save(history);
    }

    protected abstract JobPerformingStatus perform(final J job);
}
