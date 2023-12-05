package com.vladzuev.schedulingtask.util;

import com.vladzuev.schedulingtask.crud.entity.JobHistoryEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@UtilityClass
public final class JobHistoryEntityUtil {

    public static void checkEquals(final JobHistoryEntity expected, final JobHistoryEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTime(), actual.getTime());
        assertSame(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getJob(), actual.getJob());
    }
}
