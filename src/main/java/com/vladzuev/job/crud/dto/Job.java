package com.vladzuev.job.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.vladzuev.job.crud.model.JobRunInterval;
import com.vladzuev.job.crud.model.JobRunMode;
import com.vladzuev.job.crud.model.JobStatus;
import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class Job implements AbstractDto<Long> {
    private final Long id;
    private final JobStatus status;
    private final JobRunMode runMode;
    private final Instant initTime;
    private final JobRunInterval runInterval;
    private final User user;

    public static abstract class JobBuilder<J extends Job> {
        private Long id;
        private JobStatus status;
        private JobRunMode runMode;
        private Instant initTime;
        private JobRunInterval runInterval;
        private User user;

        public final void id(final Long id) {
            this.id = id;
        }

        public final void status(final JobStatus status) {
            this.status = status;
        }

        public final void runMode(final JobRunMode runMode) {
            this.runMode = runMode;
        }

        public final void initTime(final Instant initTime) {
            this.initTime = initTime;
        }

        public final void runInterval(final JobRunInterval runInterval) {
            this.runInterval = runInterval;
        }

        public final void user(final User user) {
            this.user = user;
        }

        public final J build() {
            return this.build(this.id, this.status, this.runMode, this.initTime, this.runInterval, this.user);
        }

        protected abstract J build(final Long id,
                                   final JobStatus status,
                                   final JobRunMode runMode,
                                   final Instant initTime,
                                   final JobRunInterval runInterval,
                                   final User user);
    }
}
