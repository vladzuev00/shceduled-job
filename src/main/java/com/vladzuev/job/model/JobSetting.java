package com.vladzuev.job.model;

import lombok.Value;

import java.time.Instant;

@Value
public class JobSetting {
    User user;
    Instant startDateTime;
    JobRunInterval runInterval;

    public int findRunIntervalInSecond() {
        return this.runInterval.findRunIntervalInSecond();
    }
}
