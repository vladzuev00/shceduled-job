package com.vladzuev.schedulingtask.util;

import com.vladzuev.schedulingtask.crud.dto.ScheduledTask;
import com.vladzuev.schedulingtask.service.scheduledtaskexecutor.ScheduledTaskExecutor;
import lombok.experimental.UtilityClass;
import org.quartz.JobDetail;

@UtilityClass
public final class JobDetailUtil {
    private static final String ATTRIBUTE_NAME_TASK = "task";
    private static final String ATTRIBUTE_NAME_TASK_EXECUTOR = "task_executor";

    public static void putTask(final JobDetail detail, final ScheduledTask task) {
        putAttribute(detail, ATTRIBUTE_NAME_TASK, task);
    }

    public static ScheduledTask findTask(final JobDetail detail) {
        return findAttribute(detail, ATTRIBUTE_NAME_TASK, ScheduledTask.class);
    }

    public static void putTaskExecutor(final JobDetail detail, final ScheduledTaskExecutor<?> executor) {
        putAttribute(detail, ATTRIBUTE_NAME_TASK_EXECUTOR, executor);
    }

    public static ScheduledTaskExecutor<?> findTaskExecutor(final JobDetail detail) {
        return findAttribute(detail, ATTRIBUTE_NAME_TASK_EXECUTOR, ScheduledTaskExecutor.class);
    }

    private static void putAttribute(final JobDetail detail, final String name, final Object attribute) {
        detail.getJobDataMap().put(name, attribute);
    }

    private static <T> T findAttribute(final JobDetail detail, final String name, final Class<T> type) {
        final Object attribute = detail.getJobDataMap().get(name);
        return type.cast(attribute);
    }
}
