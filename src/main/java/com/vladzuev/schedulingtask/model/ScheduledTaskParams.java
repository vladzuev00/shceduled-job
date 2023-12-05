package com.vladzuev.schedulingtask.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO: probably need to be removed
@RequiredArgsConstructor
@Getter
public abstract class ScheduledTaskParams {
    private final User user;
}
