package com.vladzuev.job.crud.mapper;

import com.vladzuev.job.base.AbstractContextTest;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.entity.JobHistoryEntity;
import com.vladzuev.job.crud.model.JobHistoryStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vladzuev.job.crud.model.JobHistoryStatus.SUCCESS;
import static java.time.Instant.parse;

public final class JobHistoryMapperTest extends AbstractContextTest {

    @Autowired
    private JobHistoryMapper mapper;

    @Test
    public void entityShouldBeMapped() {
        final JobHistoryEntity givenEntity = JobHistoryEntity.builder()
                .id(255L)
                .time(parse("2023-12-04T10:15:30Z"))
                .status(SUCCESS)
                .job(
                        JobEntity.builder()
                                .id(256L)
                                .build()
                )
                .build();
    }


}
