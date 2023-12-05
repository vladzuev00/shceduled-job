package com.vladzuev.schedulingtask.model;

import lombok.Value;

import java.time.Instant;

@Value
public class SchedulingConfiguration {
    Instant startDateTime;
    ScheduledTaskRunInterval runInterval;

    public int findRunIntervalInSecond() {
        return this.runInterval.findRunIntervalInSecond();
    }
}
