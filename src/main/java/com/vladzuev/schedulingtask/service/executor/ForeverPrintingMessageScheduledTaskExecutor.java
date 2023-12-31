package com.vladzuev.schedulingtask.service.executor;

import com.vladzuev.schedulingtask.model.task.foreverrepeated.ForeverPrintingMessageScheduledTask;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
public final class ForeverPrintingMessageScheduledTaskExecutor extends ScheduledTaskExecutor<ForeverPrintingMessageScheduledTask> {

    public ForeverPrintingMessageScheduledTaskExecutor() {
        super(ForeverPrintingMessageScheduledTask.class);
    }

    @Override
    protected void executeConcreteTask(final ForeverPrintingMessageScheduledTask task) {
        final String message = task.getMessage();
        out.println(message);
    }
}
