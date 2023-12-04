package com.vladzuev.job.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.job.crud.model.JobPerformingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
@Builder
public class JobHistory implements AbstractDto<Long> {
    Long id;
    Instant time;
    JobPerformingStatus performingStatus;
    Long jobId;
}
