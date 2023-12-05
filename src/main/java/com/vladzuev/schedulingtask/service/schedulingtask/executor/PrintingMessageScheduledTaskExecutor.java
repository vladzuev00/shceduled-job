package com.vladzuev.schedulingtask.service.schedulingtask.executor;

import com.vladzuev.schedulingtask.crud.dto.scheduledtask.PrintingMessageScheduledTask;
import org.springframework.stereotype.Service;

import static java.lang.System.out;

@Service
public final class PrintingMessageScheduledTaskExecutor extends ScheduledTaskExecutor<PrintingMessageScheduledTask> {

    public PrintingMessageScheduledTaskExecutor() {
        super(PrintingMessageScheduledTask.class);
    }

    @Override
    protected void executeConcreteTask(final PrintingMessageScheduledTask task) {
        final String message = task.getMessage();
        out.println(message);
    }
}
