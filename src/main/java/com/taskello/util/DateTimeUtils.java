package com.taskello.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

/**
 * Utility class for working with {@link OffsetDateTime}, including truncation and UTC normalization.
 */
public final class DateTimeUtils {
    // todo: Make sure Json is always serialized with UTC, too.
    private DateTimeUtils() {
    }

    /**
     * Truncates the given {@link OffsetDateTime} to minutes precision and converts it to UTC.
     * If the input is {@code null}, this method returns {@code null}.
     *
     * @param offsetDateTime the datetime to truncate and normalize
     * @return the truncated {@link OffsetDateTime} in UTC, or {@code null} if input is {@code null}
     */
    public static OffsetDateTime truncateToMinutesOrNullAndConvertToUtc(final OffsetDateTime offsetDateTime) {
        return truncateOrNullAndConvertToUtc(offsetDateTime, ChronoUnit.MINUTES);
    }

    /**
     * Truncates the given {@link OffsetDateTime} to the specified temporal unit and converts it to UTC.
     * If the input is {@code null}, this method returns {@code null}.
     *
     * @param offsetDateTime the datetime to truncate and normalize
     * @param unit the {@link TemporalUnit} to truncate to (e.g., {@link ChronoUnit#MINUTES})
     * @return the truncated {@link OffsetDateTime} in UTC, or {@code null} if input is {@code null}
     */
    public static OffsetDateTime truncateOrNullAndConvertToUtc(final OffsetDateTime offsetDateTime,
                                                               final TemporalUnit unit) {
        return Optional.ofNullable(offsetDateTime)
                .map(odt -> odt.truncatedTo(unit))
                .map(odt -> odt.withOffsetSameInstant(ZoneOffset.UTC))
                .orElse(null);
    }

    /**
     * Returns the current {@link OffsetDateTime} in UTC, truncated to minutes precision.
     *
     * @return the current UTC {@link OffsetDateTime} with seconds and nanoseconds set to zero
     */
    public static OffsetDateTime nowUtcTruncatedToMinute() {
        return OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.MINUTES);
    }

}
