package com.vladzuev.schedulingtask;

import com.vladzuev.schedulingtask.crud.dto.User;
import com.vladzuev.schedulingtask.crud.dto.scheduledtask.ForeverPrintingMessageScheduledTask;
import com.vladzuev.schedulingtask.crud.dto.scheduledtask.OncePrintingMessageScheduledTask;
import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import com.vladzuev.schedulingtask.service.schedulingtask.SchedulingTaskService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import static com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval.JobRunIntervalScale.SECOND;
import static java.time.Instant.now;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationRunner {

    public static void main(final String... args) {
        final ConfigurableApplicationContext context = run(ApplicationRunner.class, args);

        context.getBean(SchedulingTaskService.class)
                .schedule(
                        new ForeverPrintingMessageScheduledTask(
                                255L,
                                now().plusSeconds(10),
                                new User(256L),
                                new ScheduledTaskRunInterval(5, SECOND),
                                "forever-message"
                        )
                );

        context.getBean(SchedulingTaskService.class)
                .schedule(
                        new OncePrintingMessageScheduledTask(
                                257L,
                                now().plusSeconds(5),
                                new User(258L),
                                "second-message"
                        )
                );

        System.out.println("jobs registered");
    }

}
