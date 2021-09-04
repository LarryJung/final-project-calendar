package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.DtoConverter;
import com.larry.fc.finalproject.api.dto.ForListScheduleDto;
import com.larry.fc.finalproject.api.dto.SharedScheduleDto;
import com.larry.fc.finalproject.core.domain.entity.Share;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.service.UserService;
import com.larry.fc.finalproject.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Larry
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryService {
    private final UserService userService;
    private final ShareService shareService;
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

    private List<ForListScheduleDto> getSchedulesByPeriod(AuthUser authUser, Period period) {
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
        )
                     .collect(toList());
    }

    public List<SharedScheduleDto> getSharedSchedulesByDay(LocalDate date,
                                                           AuthUser authUser) {
        return Stream.concat(shareService.findSharedUserIdsByUser(authUser)
                                         .stream(),
                             Stream.of(authUser.getId()))
                     .map(userId -> SharedScheduleDto.builder()
                                                     .userId(userId)
                                                     .name(userService.getOrThrowById(userId)
                                                                      .getName())
                                                     .me(userId.equals(authUser.getId()))
                                                     .schedules(getSchedulesByDay(date,
                                                                                  AuthUser.of(userId)))
                                                     .build()
                     )
                     .collect(toList());
    }

    public List<SharedScheduleDto> getSharedSchedulesByWeek(LocalDate startOfWeek,
                                                            AuthUser authUser) {
        return Stream.concat(shareService.findSharedUserIdsByUser(authUser)
                                         .stream(),
                             Stream.of(authUser.getId()))
                     .map(userId -> SharedScheduleDto.builder()
                                                     .userId(userId)
                                                     .name(userService.getOrThrowById(userId)
                                                                      .getName())
                                                     .me(userId.equals(authUser.getId()))
                                                     .schedules(getSchedulesByWeek(startOfWeek,
                                                                                   AuthUser.of(
                                                                                           userId)))
                                                     .build()

                     )
                     .collect(toList());
    }

    public List<SharedScheduleDto> getSharedSchedulesByMonth(YearMonth yearMonth,
                                                             AuthUser authUser) {
        return Stream.concat(shareService.findSharedUserIdsByUser(authUser)
                                         .stream(),
                             Stream.of(authUser.getId()))
                     .map(userId -> SharedScheduleDto.builder()
                                                     .userId(userId)
                                                     .name(userService.getOrThrowById(userId)
                                                                      .getName())
                                                     .me(userId.equals(authUser.getId()))
                                                     .schedules(getSchedulesByMonth(yearMonth,
                                                                                    AuthUser.of(
                                                                                            userId)))
                                                     .build()

                     )
                     .collect(toList());
    }

    public List<SharedScheduleDto> getSharedSchedulesByDay2(LocalDate date,
                                                            AuthUser authUser) {
        return getSharedSchedulesByFunction(authUser,
                                            (Long userId) -> getSchedulesByDay(date,
                                                                               AuthUser.of(userId)));
    }

    public List<SharedScheduleDto> getSharedSchedulesByWeek2(LocalDate startOfWeek,
                                                             AuthUser authUser) {
        return getSharedSchedulesByFunction(authUser,
                                            (Long userId) -> getSchedulesByWeek(startOfWeek,
                                                                                AuthUser.of(userId)));
    }

    public List<SharedScheduleDto> getSharedSchedulesByMonth2(YearMonth yearMonth,
                                                              AuthUser authUser) {
        return getSharedSchedulesByFunction(authUser,
                                            (Long userId) -> getSchedulesByMonth(yearMonth,
                                                                                 AuthUser.of(userId)));
    }

    private List<SharedScheduleDto> getSharedSchedulesByFunction(AuthUser authUser,
                                                                 Function<Long,
                                                                         List<ForListScheduleDto>> function) {
        return Stream.concat(shareService.findSharedUserIdsByUser(authUser)
                                         .stream(),
                             Stream.of(authUser.getId())
        )
                     .map(userId -> SharedScheduleDto.builder()
                                                     .userId(userId)
                                                     .name(userService.getOrThrowById(userId)
                                                                      .getName())
                                                     .me(userId.equals(authUser.getId()))
                                                     .schedules(function.apply(userId))
                                                     .build()

                     )
                     .collect(toList());
    }

}
