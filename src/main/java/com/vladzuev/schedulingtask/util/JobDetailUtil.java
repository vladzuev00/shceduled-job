package com.vladzuev.schedulingtask.util;

import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import lombok.experimental.UtilityClass;
import org.quartz.JobDetail;

@UtilityClass
public final class JobDetailUtil {
    private static final String ATTRIBUTE_NAME_TASK = "task";

    public static void putTask(final JobDetail detail, final ScheduledTask task) {
        putAttribute(detail, ATTRIBUTE_NAME_TASK, task);
    }

    public static ScheduledTask findTask(final JobDetail detail) {
        return findAttribute(detail, ATTRIBUTE_NAME_TASK, ScheduledTask.class);
    }

    @SuppressWarnings("SameParameterValue")
    private static void putAttribute(final JobDetail detail, final String name, final Object attribute) {
        detail.getJobDataMap().put(name, attribute);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T> T findAttribute(final JobDetail detail, final String name, final Class<T> type) {
        final Object attribute = detail.getJobDataMap().get(name);
        return type.cast(attribute);
    }
}
