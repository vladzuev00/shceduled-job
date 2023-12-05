package com.vladzuev.schedulingtask;

import com.vladzuev.schedulingtask.crud.dto.User;
import com.vladzuev.schedulingtask.crud.dto.scheduledtask.PrintingMessageScheduledTask;
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
                        new PrintingMessageScheduledTask(
                                255L,
                                new SchedulingConfiguration(now().plusSeconds(10), new ScheduledTaskRunInterval(5, SECOND)),
                                new User(256L),
                                "first-message"
                        )
                );

        context.getBean(SchedulingTaskService.class)
                .schedule(
                        new PrintingMessageScheduledTask(
                                257L,
                                new SchedulingConfiguration(now().plusSeconds(10), new ScheduledTaskRunInterval(5, SECOND)),
                                new User(258L),
                                "second-message"
                        )
                );

        System.out.println("jobs registered");
    }

}
