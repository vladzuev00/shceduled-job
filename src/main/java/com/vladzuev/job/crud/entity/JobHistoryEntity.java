package com.vladzuev.job.crud.entity;

import by.nhorushko.crudgeneric.v2.domain.AbstractEntity;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladzuev.job.crud.model.JobPerformingStatus;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "job_histories")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JobHistoryEntity implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private Instant time;

    @Enumerated(STRING)
    @Column(name = "completion_status")
    @Type(type = "pgsql_enum")
    private JobPerformingStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    private JobEntity job;
}
