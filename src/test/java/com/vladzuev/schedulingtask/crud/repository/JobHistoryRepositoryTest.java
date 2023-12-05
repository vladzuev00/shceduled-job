package com.vladzuev.schedulingtask.crud.repository;

import com.vladzuev.schedulingtask.base.AbstractContextTest;
import com.vladzuev.schedulingtask.crud.entity.JobEntity;
import com.vladzuev.schedulingtask.crud.entity.JobHistoryEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.vladzuev.schedulingtask.crud.model.JobPerformingStatus.SUCCESS;
import static com.vladzuev.schedulingtask.util.JobHistoryEntityUtil.checkEquals;
import static java.time.Instant.parse;
import static org.junit.Assert.assertTrue;

public final class JobHistoryRepositoryTest extends AbstractContextTest {

    @Autowired
    private JobHistoryRepository repository;

    @Test
    @Sql("classpath:sql-scripts/insert-job-history.sql")
    public void historyShouldBeFoundById() {
        final Long givenId = 257L;

        super.startQueryCount();
        final Optional<JobHistoryEntity> optionalActual = this.repository.findById(givenId);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isPresent());
        final JobHistoryEntity actual = optionalActual.get();
        final JobHistoryEntity expected = JobHistoryEntity.builder()
                .id(257L)
                .time(parse("2023-04-12T13:19:35Z"))
                .status(SUCCESS)
                .job(super.entityManager.getReference(JobEntity.class, 256L))
                .build();
        checkEquals(expected, actual);
    }

    @Test
    @Sql("classpath:sql-scripts/insert-job.sql")
    public void historyShouldBeSaved() {
        final JobHistoryEntity givenHistory = JobHistoryEntity.builder()
                .time(parse("2023-04-12T13:19:35Z"))
                .status(SUCCESS)
                .job(super.entityManager.getReference(JobEntity.class, 256L))
                .build();

        super.startQueryCount();
        this.repository.save(givenHistory);
        super.checkQueryCount(1);
    }
}
