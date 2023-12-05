package com.vladzuev.schedulingtask.service.scheduledtask;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.schedulingtask.model.SchedulingConfiguration;
import com.vladzuev.schedulingtask.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTask implements AbstractDto<Long> {
    private final Long id;
    private final SchedulingConfiguration configuration;
    private final User user;
}
