package com.vladzuev.job.service.factory;

import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.model.JobPerformingStatus;

import java.time.Instant;

import static java.time.Instant.now;

public final class JobHistoryFactory {

    public JobHistory create(final Job job, final JobPerformingStatus performingStatus) {
        final Instant time = now();
        final Long jobId = job.getId();
        return JobHistory.builder()
                .time(time)
                .performingStatus(performingStatus)
                .jobId(jobId)
                .build();
    }

}
