package com.vladzuev.job.model;

import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public abstract class ScheduledTask {
    private final Instant startDateTime;
}
