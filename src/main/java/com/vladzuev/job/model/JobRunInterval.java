package com.vladzuev.job.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class JobRunInterval {
    int runIntervalValue;
    JobRunIntervalScale runIntervalScale;

    public int findRunIntervalInSecond() {
        return this.runIntervalValue * this.runIntervalScale.secondCount;
    }

    @RequiredArgsConstructor
    public enum JobRunIntervalScale {
        SECOND(1),
        MINUTE(SECOND.secondCount * 60),
        HOUR(MINUTE.secondCount * 60),
        DAY(HOUR.secondCount * 24),
        WEEK(DAY.secondCount * 7);

        @Getter
        private final int secondCount;
    }
}
