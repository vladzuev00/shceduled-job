package com.vladzuev.job.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.job.crud.model.JobHistoryStatus;
import lombok.Value;

import java.time.Instant;

@Value
public class JobHistory implements AbstractDto<Long> {
    Long id;
    Instant time;
    JobHistoryStatus status;
    Long jobId;
}
