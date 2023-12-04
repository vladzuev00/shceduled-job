package com.vladzuev.job.crud.mapper;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.entity.JobHistoryEntity;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;

public final class JobHistoryMapper extends AbsMapperEntityDto<JobHistoryEntity, JobHistory> {

    public JobHistoryMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, entityManager, JobHistoryEntity.class, JobHistory.class);
    }

    @Override
    protected JobHistory create(final JobHistoryEntity entity) {
        return new JobHistory(
                entity.getId(),
                entity.getTime(),
                entity.getStatus(),
                this.mapJob(entity)
        );
    }

    private Job mapJob(final JobHistoryEntity entity) {
        final JobEntity job = entity.getJob();
        return super.modelMapper.map(job, Job.class);
    }
}
