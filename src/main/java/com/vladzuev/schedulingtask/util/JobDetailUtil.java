package com.vladzuev.schedulingtask.util;

import com.vladzuev.schedulingtask.service.jobtask.ScheduledTask;
import lombok.experimental.UtilityClass;
import org.quartz.JobDetail;

@UtilityClass
public final class JobDetailUtil {
    private static final String ATTRIBUTE_NAME_TASK = "task";

    public static void putTask(final JobDetail detail, final ScheduledTask<?> task) {
        detail.getJobDataMap().put(ATTRIBUTE_NAME_TASK, task);
    }

    public static ScheduledTask<?> findTask(final JobDetail detail) {
        return (ScheduledTask<?>) detail.getJobDataMap().get(ATTRIBUTE_NAME_TASK);
    }
}
