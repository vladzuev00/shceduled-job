package com.vladzuev.job.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.job.crud.model.JobRunInterval;
import com.vladzuev.job.crud.model.JobRunMode;
import com.vladzuev.job.crud.model.JobStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class Job implements AbstractDto<Long> {
    private final Long id;
    private final JobStatus status;
    private final JobRunMode runMode;
    private final Instant initTime;
    private final JobRunInterval runInterval;
    private final User user;
}
