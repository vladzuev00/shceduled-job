package com.vladzuev.job.quartz.service;

import com.vladzuev.job.crud.dto.User;
import com.vladzuev.job.quartz.AbstractJob;
import com.vladzuev.job.quartz.model.JobRegistrationContext;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@RequiredArgsConstructor
public final class JobRegistrationService {
    private final JobAttributeManager jobAttributeManager;
    private final Scheduler scheduler;

    public void register(final AbstractJob job, final JobRegistrationContext context) {
        final JobDetail jobDetail = this.createJobDetail(job, context);
        final Trigger trigger = createTrigger(context);
        this.jobAttributeManager.putUser(jobDetail, context.getUser());
    }

    private JobDetail createJobDetail(final AbstractJob job, final JobRegistrationContext context) {
        final Class<? extends AbstractJob> jobType = job.getClass();
        final JobDetail jobDetail = newJob(jobType).build();
        this.putUserAttribute(jobDetail, context);
        return jobDetail;
    }

    private void putUserAttribute(final JobDetail jobDetail, final JobRegistrationContext context) {
        final User user = context.getUser();
        this.jobAttributeManager.putUser(jobDetail, user);
    }

    private static Trigger createTrigger(final JobRegistrationContext context) {
        final
        return newTrigger().startAt()
    }
}
