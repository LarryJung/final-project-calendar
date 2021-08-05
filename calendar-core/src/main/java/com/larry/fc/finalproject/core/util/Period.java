package com.larry.fc.finalproject.core.util;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Larry
 */
@Getter
public class Period {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    private Period(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Period of(LocalDateTime startAt, LocalDateTime endAt) {
        return new Period(startAt, endAt == null ? startAt : endAt);
    }

    public static Period of(LocalDate startAt, LocalDate endAt) {
        return new Period(startAt.atStartOfDay(),
                endAt == null ? startAt.atStartOfDay() : endAt.atStartOfDay());
    }

    public static Period of(LocalDate date) {
        return new Period(date.atStartOfDay(), date.atStartOfDay());
    }

    public static Period of(LocalDateTime startAt) {
        return new Period(startAt, startAt);
    }

    public boolean isOverlapped(Period period) {
        return isOverlapped(period.startAt, period.endAt);
    }
    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.startAt.isBefore(endAt) && startAt.isBefore(this.endAt);
    }

    public boolean isOverlapped(LocalDate date) {
        return isOverlapped(date.atStartOfDay(),
                LocalDateTime.of(date, LocalTime.of(23, 59, 59, 999999999)));
    }

    @Override
    public String toString() {
        return "Period{" +
                "startAt=" + startAt +
                ", endAt=" + endAt +
                '}';
    }
}
