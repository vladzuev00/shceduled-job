package com.vladzuev.job.crud.mapper;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import com.vladzuev.job.crud.dto.JobHistory;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.entity.JobHistoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
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
                extractJobId(entity)
        );
    }

    @Override
    protected void mapSpecificFields(final JobHistory source, final JobHistoryEntity destination) {
        this.setJob(destination, source);
    }

    private static Long extractJobId(final JobHistoryEntity entity) {
        return entity.getJob().getId();
    }

    private void setJob(final JobHistoryEntity destination, final JobHistory source) {
        final Long jobId = source.getJobId();
        final JobEntity job = super.entityManager.getReference(JobEntity.class, jobId);
        destination.setJob(job);
    }
}
