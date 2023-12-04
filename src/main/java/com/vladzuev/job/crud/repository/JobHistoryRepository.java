package com.vladzuev.job.crud.repository;

import com.vladzuev.job.crud.entity.JobHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistoryEntity, Long> {

}
