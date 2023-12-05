package com.vladzuev.schedulingtask.crud.dto.scheduledtask;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.schedulingtask.crud.dto.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class ScheduledTask implements AbstractDto<Long> {
    private final Long id;
    private final Instant startDateTime;
    private final User user;
}
