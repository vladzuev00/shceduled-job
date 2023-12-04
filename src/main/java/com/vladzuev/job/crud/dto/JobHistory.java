package com.vladzuev.job.crud.dto;

import com.vladzuev.job.crud.model.JobHistoryStatus;
import lombok.Value;

import java.time.Instant;

@Value
public class JobHistory {
    Long id;
    Instant time;
    JobHistoryStatus status;
    Job job;
}
