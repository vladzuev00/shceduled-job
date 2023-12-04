package com.vladzuev.job.crud.mapper;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import com.vladzuev.job.crud.dto.Job;
import com.vladzuev.job.crud.dto.Job.JobBuilder;
import com.vladzuev.job.crud.dto.User;
import com.vladzuev.job.crud.entity.JobEntity;
import com.vladzuev.job.crud.entity.UserEntity;
import com.vladzuev.job.crud.model.JobRunInterval;
import com.vladzuev.job.crud.model.JobRunIntervalScale;
import com.vladzuev.job.crud.model.JobType;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public abstract class JobMapper<J extends Job, B extends JobBuilder<J>> extends AbsMapperEntityDto<JobEntity, J> {
    private final Class<B> jobBuilderType;

    public JobMapper(final ModelMapper modelMapper,
                     final EntityManager entityManager,
                     final Class<J> jobType,
                     final Class<B> jobBuilderType) {
        super(modelMapper, entityManager, JobEntity.class, jobType);
        this.jobBuilderType = jobBuilderType;
    }

    @Override
    protected final J create(final JobEntity entity) {
        final B builder = this.findJobBuilder(entity);
        this.accumulateComponentsExceptParams(builder, entity);
        final String params = entity.getParams();
        this.accumulateParams(builder, params);
        return builder.build();
    }

    @Override
    protected final void mapSpecificFields(final J source, final JobEntity destination) {
        this.accumulateRunIntervalValue(destination, source);
        this.accumulateRunIntervalScale(destination, source);
        this.accumulateParams(destination, source);
    }

    protected abstract void accumulateParams(final B builder, final String params);

    protected abstract String extractParams(final J jobs);

    private B findJobBuilder(final JobEntity entity) {
        final JobType jobType = entity.getType();
        final JobBuilder<?> jobBuilder = jobType.getJobBuilder();
        return this.jobBuilderType.cast(jobBuilder);
    }

    private void accumulateComponentsExceptParams(final B builder, final JobEntity entity) {
        this.accumulateId(builder, entity);
        this.accumulateStatus(builder, entity);
        this.accumulateRunMode(builder, entity);
        this.accumulateInitTime(builder, entity);
        this.accumulateRunInterval(builder, entity);
        this.accumulateUser(builder, entity);
    }

    private void accumulateId(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, JobEntity::getId, B::id);
    }

    private void accumulateStatus(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, JobEntity::getStatus, B::status);
    }

    private void accumulateRunMode(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, JobEntity::getRunMode, B::runMode);
    }

    private void accumulateInitTime(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, JobEntity::getInitTime, B::initTime);
    }

    private void accumulateRunInterval(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, JobMapper::extractRunInterval, B::runInterval);
    }

    private void accumulateUser(final B builder, final JobEntity entity) {
        accumulateComponent(builder, entity, this::extractUser, B::user);
    }

    private static JobRunInterval extractRunInterval(final JobEntity entity) {
        final int value = entity.getRunIntervalValue();
        final JobRunIntervalScale scale = entity.getRunIntervalScale();
        return new JobRunInterval(value, scale);
    }

    private User extractUser(final JobEntity entity) {
        final UserEntity user = entity.getUser();
        return super.modelMapper.map(user, User.class);
    }

    private void accumulateRunIntervalValue(final JobEntity entity, final J dto) {
        accumulateIntComponent(entity, dto, this::extractRunIntervalValue, JobEntity::setRunIntervalValue);
    }

    private void accumulateRunIntervalScale(final JobEntity entity, final J dto) {
        accumulateComponent(entity, dto, this::extractRunIntervalScale, JobEntity::setRunIntervalScale);
    }

    private void accumulateParams(final JobEntity entity, final J dto) {
        accumulateComponent(entity, dto, this::extractParams, JobEntity::setParams);
    }

    private int extractRunIntervalValue(final J job) {
        return job.getRunInterval().getValue();
    }

    private JobRunIntervalScale extractRunIntervalScale(final J job) {
        return job.getRunInterval().getScale();
    }

    private static <A, S, C> void accumulateComponent(final A accumulator,
                                                      final S source,
                                                      final Function<S, C> extractor,
                                                      final BiConsumer<A, C> setter) {
        final C component = extractor.apply(source);
        setter.accept(accumulator, component);
    }

    private static <A, S> void accumulateIntComponent(final A accumulator,
                                                      final S source,
                                                      final ToIntFunction<S> extractor,
                                                      final BiSecondIntConsumer<A> setter) {
        final int component = extractor.applyAsInt(source);
        setter.accept(accumulator, component);
    }

    @FunctionalInterface
    private interface BiSecondIntConsumer<T> {
        void accept(final T first, final int second);
    }
}
