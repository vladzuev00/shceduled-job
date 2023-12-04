package com.vladzuev.job.crud.entity;

import by.nhorushko.crudgeneric.v2.domain.AbstractEntity;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladzuev.job.crud.model.JobRunIntervalScale;
import com.vladzuev.job.crud.model.JobRunMode;
import com.vladzuev.job.crud.model.JobStatus;
import com.vladzuev.job.crud.model.JobType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "jobs")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JobEntity implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "status")
    @Type(type = "pgsql_enum")
    private JobStatus status;

    @Enumerated(STRING)
    @Column(name = "type")
    @Type(type = "pgsql_enum")
    private JobType type;

    @Enumerated(STRING)
    @Column(name = "run_mode")
    @Type(type = "pgsql_enum")
    private JobRunMode runMode;

    @Column(name = "init_time")
    private Instant initTime;

    @Column(name = "run_interval_value")
    private int runIntervalValue;

    @Enumerated(STRING)
    @Column(name = "run_interval_scale")
    @Type(type = "pgsql_enum")
    private JobRunIntervalScale runIntervalScale;

    @Type(type = "jsonb")
    @Column(name = "params", columnDefinition = "jsonb")
    private String params;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;
}
