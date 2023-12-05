package com.vladzuev.job.service.job;

import com.vladzuev.job.model.User;
import com.vladzuev.job.service.JobAttributeManager;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

@RequiredArgsConstructor
public abstract class AbstractJob implements Job {
    private final JobAttributeManager jobAttributeManager;

    @Override
    public final void execute(final JobExecutionContext context) {
        final JobDetail jobDetail = context.getJobDetail();
        final User user = this.jobAttributeManager.findUser(jobDetail);
        this.executeFor(user);
    }

    protected abstract void executeFor(final User user);
}
