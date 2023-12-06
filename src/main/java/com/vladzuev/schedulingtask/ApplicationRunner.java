package com.vladzuev.schedulingtask;

import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import com.vladzuev.schedulingtask.model.task.ScheduledTask;
import com.vladzuev.schedulingtask.model.task.foreverrepeated.ForeverPrintingMessageScheduledTask;
import com.vladzuev.schedulingtask.model.task.oncerepeated.OncePrintingMessageScheduledTask;
import com.vladzuev.schedulingtask.service.schedulingtask.ScheduledTaskManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import static com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval.JobRunIntervalScale.SECOND;
import static java.time.Instant.now;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationRunner {

    public static void main(final String... args) throws InterruptedException {
        final ConfigurableApplicationContext context = run(ApplicationRunner.class, args);

        final ScheduledTaskManager taskManager = context.getBean(ScheduledTaskManager.class);

        final ScheduledTask foreverTask = new ForeverPrintingMessageScheduledTask(
                255L,
                now().plusSeconds(10),
                new ScheduledTaskRunInterval(5, SECOND),
                "forever-message"
        );
        taskManager.schedule(foreverTask);

        final ScheduledTask onceTask = new OncePrintingMessageScheduledTask(
                257L,
                now().plusSeconds(5),
                "once-message"
        );
        taskManager.schedule(onceTask);

        System.out.println("jobs registered");

        SECONDS.sleep(20);
        taskManager.pause(foreverTask);
        System.out.println("forever task was paused");
    }

}
