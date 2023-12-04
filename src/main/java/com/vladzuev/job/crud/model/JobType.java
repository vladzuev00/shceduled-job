package com.vladzuev.job.crud.model;

import com.vladzuev.job.crud.dto.Job.JobBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobType {
    REPORT_SENDER(null);

    private final JobBuilder<?> jobBuilder;
}
