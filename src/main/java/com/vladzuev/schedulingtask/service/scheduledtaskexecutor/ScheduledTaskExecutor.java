package com.vladzuev.schedulingtask.service.scheduledtaskexecutor;

import com.vladzuev.schedulingtask.crud.dto.ScheduledTask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTaskExecutor<T extends ScheduledTask> {
    private final Class<T> taskType;


}
