package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.DtoConverter;
import com.larry.fc.finalproject.api.dto.ForListScheduleDto;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Larry
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    public List<ForListScheduleDto> getSchedulesByDay(LocalDate date, AuthUser authUser) {
        final Period period = Period.of(date, date);
        return getSchedulesByPeriod(authUser, period);
    }

    public List<ForListScheduleDto> getSchedulesByWeek(LocalDate startOfWeek, AuthUser authUser) {
        final Period period = Period.of(startOfWeek, startOfWeek.plusDays(6));
        return getSchedulesByPeriod(authUser, period);
    }

    public List<ForListScheduleDto> getSchedulesByMonth(YearMonth yearMonth, AuthUser authUser) {
        final Period period = Period.of(yearMonth.atDay(1), yearMonth.atEndOfMonth());
        return getSchedulesByPeriod(authUser, period);
    }

    public List<ForListScheduleDto> getSchedulesByPeriod(AuthUser authUser, Period period) {
        return Stream.concat(
                scheduleRepository
                        .findAllByWriter_Id(authUser.getId())
                        .stream()
                        .filter(schedule -> schedule.isOverlapped(period))
                        .map(schedule -> DtoConverter.toForListDto(schedule)),
                engagementRepository
                        .findAllByAttendeeId(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(period))
                        .map(engagement -> DtoConverter.toForListDto(engagement.getSchedule()))
        ).collect(toList());
    }
}
