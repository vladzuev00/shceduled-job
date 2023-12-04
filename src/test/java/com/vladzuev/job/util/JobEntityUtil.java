package com.vladzuev.job.util;

import com.vladzuev.job.crud.entity.JobEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@UtilityClass
public final class JobEntityUtil {

    public static void checkEquals(final JobEntity expected, final JobEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertSame(expected.getStatus(), actual.getStatus());
        assertSame(expected.getType(), actual.getType());
        assertSame(expected.getRunMode(), actual.getRunMode());
        assertEquals(expected.getInitTime(), actual.getInitTime());
        assertEquals(expected.getRunIntervalValue(), actual.getRunIntervalValue());
        assertSame(expected.getRunIntervalScale(), actual.getRunIntervalScale());
        assertEquals(expected.getParams(), actual.getParams());
        assertEquals(expected.getUser(), actual.getUser());
    }

    public static JobEntity createJobEntity(final Long id) {
        return JobEntity.builder()
                .id(id)
                .build();
    }
}
