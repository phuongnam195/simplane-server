package hcmus.nmq.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1:40 PM 4/17/2022
 * LeHongQuan
 */

public class Extensions {
    public static boolean isBlankOrNull(String str) {
        return str == null || str.isEmpty();
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> Stream<T> toStream(Collection<T> collection) {
        return Optional.ofNullable(collection).map(Collection::stream).orElseGet(Stream::empty);
    }

    public static <T> List<T> toList(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    public static <T> List<T> toList(Iterable<T> iterator) {
        List<T> result = new ArrayList<>();
        iterator.forEach(result::add);
        return result;
    }

    public static <T> Map<String, T> toMap(Stream<T> stream, Function<? super T, ? extends String> keyMapper) {
        return stream.collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    public static <T> Collection<T> merge(Collection<T> from, Collection<T> to) {
        if (from == null) {
            from = new ArrayList<>(to);
        } else {
            from.removeIf(t -> !to.contains(t));
        }
        return from;
    }

    public static <T> Optional<T> toOptional(T value) {
        return Optional.ofNullable(value);
    }
}