package com.vladzuev.schedulingtask.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@UtilityClass
public final class CollectionUtil {

    public static <K, V> Map<K, V> collectToMap(final Collection<V> values, final Function<V, K> keyExtractor) {
        return values.stream()
                .collect(
                        toMap(
                                keyExtractor,
                                identity()
                        )
                );
    }
}
