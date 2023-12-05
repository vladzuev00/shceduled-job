package com.vladzuev.schedulingtask.crud.mapper;

import com.vladzuev.schedulingtask.base.AbstractContextTest;
import com.vladzuev.schedulingtask.crud.entity.JobEntity;
import com.vladzuev.schedulingtask.crud.entity.JobHistoryEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.vladzuev.schedulingtask.crud.model.JobPerformingStatus.SUCCESS;
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
