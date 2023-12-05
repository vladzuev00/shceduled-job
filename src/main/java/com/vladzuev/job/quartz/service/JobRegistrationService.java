package com.vladzuev.job.quartz.service;

import com.vladzuev.job.crud.dto.User;
import com.vladzuev.job.quartz.AbstractJob;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;

@Service
@RequiredArgsConstructor
public final class JobRegistrationService {
    private final Scheduler scheduler;

    public void register(final AbstractJob job, final User user) {
        final JobDetail jobDetail = createJobDetail(job, user);

    }

    private static JobDetail createJobDetail(final AbstractJob job, final User user) {
        final JobDetail jobDetail = newJob(job.getClass()).build();
        jobDetail.getJobDataMap().put();
    }
}
