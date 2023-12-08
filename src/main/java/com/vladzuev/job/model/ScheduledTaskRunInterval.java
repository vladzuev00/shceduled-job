package com.vladzuev.job.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class ScheduledTaskRunInterval {
    int runIntervalValue;
    JobRunIntervalScale runIntervalScale;

    public int findRunIntervalInMillis() {
        return this.runIntervalValue * this.runIntervalScale.millis;
    }

    @RequiredArgsConstructor
    public enum JobRunIntervalScale {
        SECOND(1000),
        MINUTE(SECOND.millis * 60),
        HOUR(MINUTE.millis * 60),
        DAY(HOUR.millis * 24),
        WEEK(DAY.millis * 7);

        @Getter
        private final int millis;
    }
}
