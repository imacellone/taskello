package com.taskello.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

public class DateTimeUtils {

    public static OffsetDateTime truncateToMinutesOrNull(final OffsetDateTime offsetDateTime) {
        return truncateOrNull(offsetDateTime, ChronoUnit.MINUTES);
    }

    public static OffsetDateTime truncateOrNull(final OffsetDateTime offsetDateTime, final TemporalUnit unit) {
        return Optional.ofNullable(offsetDateTime).map(odt -> odt.truncatedTo(unit)).orElse(null);
    }

    public static OffsetDateTime nowTruncatedToMinute() {
        return OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

}
