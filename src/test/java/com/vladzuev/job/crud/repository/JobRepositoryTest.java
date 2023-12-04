package com.vladzuev.job.crud.repository;

import com.vladzuev.job.base.AbstractContextTest;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.entity.UserEntity;
import com.vladzuev.job.crud.repository.JobRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.vladzuev.job.crud.model.JobRunIntervalScale.DAY;
import static com.vladzuev.job.crud.model.JobRunMode.PERIODIC;
import static com.vladzuev.job.crud.model.JobStatus.ACTIVE;
import static com.vladzuev.job.crud.model.JobType.REPORT_SENDER;
import static com.vladzuev.job.util.JobEntityUtil.checkEquals;
import static java.time.Instant.parse;
import static org.junit.Assert.assertTrue;

public final class JobRepositoryTest extends AbstractContextTest {

    @Autowired
    private JobRepository repository;

    @Test
    @Sql("classpath:sql-scripts/insert-job.sql")
    public void jobShouldBeFoundById() {
        final Long givenId = 256L;

        super.startQueryCount();
        final Optional<JobEntity> optionalActual = this.repository.findById(givenId);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isPresent());
        final JobEntity actual = optionalActual.get();
        final JobEntity expected = JobEntity.builder()
                .id(givenId)
                .status(ACTIVE)
                .type(REPORT_SENDER)
                .runMode(PERIODIC)
                .initTime(parse("2023-04-12T13:19:34Z"))
                .runIntervalValue(1)
                .runIntervalScale(DAY)
                .params("{\"first-property\": \"first-value\", \"second-property\": \"second-value\"}")
                .user(super.entityManager.getReference(UserEntity.class, 255L))
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void jobShouldBeSaved() {
        final JobEntity givenJob = JobEntity.builder()
                .status(ACTIVE)
                .type(REPORT_SENDER)
                .runMode(PERIODIC)
                .initTime(parse("2023-04-12T13:19:34Z"))
                .runIntervalValue(1)
                .runIntervalScale(DAY)
                .params("{\"first-property\": \"first-value\", \"second-property\": \"second-value\"}")
                .user(super.entityManager.getReference(UserEntity.class, 255L))
                .build();

        super.startQueryCount();
        this.repository.save(givenJob);
        super.checkQueryCount(1);
    }
}
