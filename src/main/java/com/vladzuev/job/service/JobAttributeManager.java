package com.vladzuev.job.service;

import com.vladzuev.job.model.User;
import org.quartz.JobDetail;
import org.springframework.stereotype.Service;

@Service
public final class JobAttributeManager {
    private static final String ATTRIBUTE_NAME_USER = "user";

    public void putUser(final JobDetail jobDetail, final User user) {
        putAttribute(jobDetail, ATTRIBUTE_NAME_USER, user);
    }

    public User findUser(final JobDetail jobDetail) {
        return findAttribute(jobDetail, ATTRIBUTE_NAME_USER, User.class);
    }

    private static void putAttribute(final JobDetail jobDetail, final String attributeName, final Object attribute) {
        jobDetail.getJobDataMap().put(attributeName, attribute);
    }

    private static <T> T findAttribute(final JobDetail jobDetail,
                                       final String attributeName,
                                       final Class<T> attributeType) {
        final Object attribute = jobDetail.getJobDataMap().get(attributeName);
        return attributeType.cast(attribute);
    }
}
