package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.type.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Larry
 */
public class CreateNotificationReqTest {
    @Test
    @DisplayName("반복없음")
    void test1() {
        LocalDateTime time = LocalDateTime.of(2021, 7, 1, 0, 0, 0);
        CreateNotificationReq req = new CreateNotificationReq("titie", time, null);
        assertEquals(List.of(time), req.getRepeatTimes());
    }

    @Test
    @DisplayName("일반복")
    void test2() {
        LocalDateTime time = LocalDateTime.of(2021, 7, 1, 0, 0, 0);
        CreateNotificationReq req = new CreateNotificationReq("titie",
                time,
                new CreateNotificationReq.RepeatInfo(
                        new CreateNotificationReq.Interval(
                                1,
                                TimeUnit.DAY),
                        3
                ));
        assertEquals(List.of(time, time.plusDays(1), time.plusDays(2)), req.getRepeatTimes());
    }

    @Test
    @DisplayName("주반복")
    void test3() {
        LocalDateTime time = LocalDateTime.of(2021, 7, 1, 0, 0, 0);
        CreateNotificationReq req = new CreateNotificationReq("titie",
                time,
                new CreateNotificationReq.RepeatInfo(
                        new CreateNotificationReq.Interval(
                                2,
                                TimeUnit.WEEK),
                        3
                ));
        assertEquals(List.of(time, time.plusDays(14), time.plusDays(28)),
                req.getRepeatTimes());
    }

    @Test
    @DisplayName("월반복")
    void test4() {
        LocalDateTime time = LocalDateTime.of(2021, 7, 1, 0, 0, 0);
        CreateNotificationReq req = new CreateNotificationReq("titie",
                time,
                new CreateNotificationReq.RepeatInfo(
                        new CreateNotificationReq.Interval(
                                1,
                                TimeUnit.MONTH),
                        3
                ));
        assertEquals(List.of(time, time.plusMonths(1), time.plusMonths(2)),
                req.getRepeatTimes());
    }

    @Test
    @DisplayName("연반복")
    void test5() {
        LocalDateTime time = LocalDateTime.of(2021, 7, 1, 0, 0, 0);
        CreateNotificationReq req = new CreateNotificationReq("titie",
                time,
                new CreateNotificationReq.RepeatInfo(
                        new CreateNotificationReq.Interval(
                                1,
                                TimeUnit.YEAR),
                        3
                ));
        assertEquals(List.of(time, time.plusYears(1), time.plusYears(2)),
                req.getRepeatTimes());
    }
}
