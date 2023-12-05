package com.vladzuev.schedulingtask.service.jobtask;

import com.vladzuev.schedulingtask.model.ScheduledTaskParams;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ScheduledTask<P extends ScheduledTaskParams> {

    @Getter
    private final P params;

    public final void execute() {
        this.execute(this.params);
    }

    protected abstract void execute(final P params);
}
