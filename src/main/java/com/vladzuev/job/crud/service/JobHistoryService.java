package com.vladzuev.job.crud.service;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.entity.JobHistoryEntity;
import com.vladzuev.job.crud.repository.JobHistoryRepository;

public class JobHistoryService extends AbsServiceCRUD<Long, JobHistoryEntity, JobHistory, JobHistoryRepository> {

    public JobHistoryService(AbsMapperEntityDto<JobHistoryEntity, JobHistory> mapper, JobHistoryRepository repository) {
        super(mapper, repository);
    }
}
