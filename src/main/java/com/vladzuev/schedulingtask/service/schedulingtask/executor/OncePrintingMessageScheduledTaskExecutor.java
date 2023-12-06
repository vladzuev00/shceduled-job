package com.vladzuev.schedulingtask.service.schedulingtask.executor;

import com.vladzuev.schedulingtask.model.task.OncePrintingMessageScheduledTask;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
public final class OncePrintingMessageScheduledTaskExecutor extends ScheduledTaskExecutor<OncePrintingMessageScheduledTask> {

    public OncePrintingMessageScheduledTaskExecutor() {
        super(OncePrintingMessageScheduledTask.class);
    }

    @Override
    protected void executeConcreteTask(final OncePrintingMessageScheduledTask task) {
        final String message = task.getMessage();
        out.println(message);
    }
}
