package com.vladzuev.job.crud.service;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.repository.JobRepository;

public abstract class JobService<J extends Job> extends AbsServiceCRUD<Long, JobEntity, J, JobRepository> {

    public JobService(final AbsMapperEntityDto<JobEntity, J> mapper, final JobRepository repository) {
        super(mapper, repository);
    }

}
