package com.vladzuev.schedulingtask;

import com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval;
import com.vladzuev.schedulingtask.model.ScheduledTaskParams;
import com.vladzuev.schedulingtask.model.User;
import com.vladzuev.schedulingtask.service.schedulingtask.SchedulingTaskService;
import com.vladzuev.schedulingtask.service.jobtask.HelloWorldScheduledTask;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import static com.vladzuev.schedulingtask.model.ScheduledTaskRunInterval.JobRunIntervalScale.SECOND;
import static java.time.Instant.parse;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApplicationRunner {

    public static void main(final String... args) {
        final ConfigurableApplicationContext context = run(ApplicationRunner.class, args);

        context.getBean(SchedulingTaskService.class)
                .schedule(new HelloWorldScheduledTask(
                        new ScheduledTaskParams(new User(255L), parse("2023-12-05T14:00:01Z"), new ScheduledTaskRunInterval(5, SECOND)){}));

        context.getBean(SchedulingTaskService.class)
                .schedule(new HelloWorldScheduledTask(
                        new ScheduledTaskParams(new User(256L), parse("2023-12-05T14:00:01Z"), new ScheduledTaskRunInterval(5, SECOND)){}));

        System.out.println("jobs registered");
    }

}
