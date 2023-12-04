package com.vladzuev.job.crud.repository;

import com.vladzuev.job.crud.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
