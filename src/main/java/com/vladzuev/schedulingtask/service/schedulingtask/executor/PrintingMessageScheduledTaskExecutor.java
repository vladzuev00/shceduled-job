package com.vladzuev.schedulingtask.service.schedulingtask.executor;

import com.vladzuev.schedulingtask.crud.dto.scheduledtask.ForeverPrintingMessageScheduledTask;
import org.springframework.stereotype.Service;

import static java.lang.System.out;

@Service
public final class PrintingMessageScheduledTaskExecutor extends ScheduledTaskExecutor<ForeverPrintingMessageScheduledTask> {

    public PrintingMessageScheduledTaskExecutor() {
        super(ForeverPrintingMessageScheduledTask.class);
    }

    @Override
    protected void executeConcreteTask(final ForeverPrintingMessageScheduledTask task) {
        final String message = task.getMessage();
        out.println(message);
    }
}
