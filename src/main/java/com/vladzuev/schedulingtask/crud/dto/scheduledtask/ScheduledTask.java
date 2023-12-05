package com.vladzuev.schedulingtask.crud.dto.scheduledtask;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.schedulingtask.crud.dto.User;
import com.vladzuev.schedulingtask.model.SchedulingConfiguration;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class ScheduledTask implements AbstractDto<Long> {
    private final Long id;
    private final SchedulingConfiguration configuration;
    private final User user;
}