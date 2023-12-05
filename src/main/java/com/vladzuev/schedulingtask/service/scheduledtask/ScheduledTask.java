package com.vladzuev.schedulingtask.service.scheduledtask;

import com.vladzuev.schedulingtask.model.ScheduledTaskParams;
import com.vladzuev.schedulingtask.model.SchedulingConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class ScheduledTask<P extends ScheduledTaskParams> {
    private final SchedulingConfiguration configuration;
    private final P params;

    //TODO: remove
    public final void execute() {
        this.execute(this.params);
    }

    protected abstract void execute(final P params);
}
