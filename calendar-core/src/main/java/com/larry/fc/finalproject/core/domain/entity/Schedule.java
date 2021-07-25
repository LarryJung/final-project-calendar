package com.larry.fc.finalproject.core.domain.entity;

import com.larry.fc.finalproject.core.domain.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;
    private User writer;
    private ScheduleType scheduleType;

    public static Schedule event(String title,
                                 String description, LocalDateTime startAt,
                                 LocalDateTime endAt, User writer) {
        return Schedule.builder()
                       .startAt(startAt)
                       .endAt(endAt)
                       .title(title)
                       .description(description)
                       .writer(writer)
                       .scheduleType(ScheduleType.EVENT)
                       .build();
    }

    public static Schedule task(String title, String description, LocalDateTime taskAt,
                                User writer) {
        return Schedule.builder()
                       .startAt(taskAt)
                       .title(title)
                       .description(description)
                       .writer(writer)
                       .scheduleType(ScheduleType.TASK)
                       .build();
    }

    public static Schedule notification(String title, LocalDateTime notifyAt, User writer) {
        return Schedule.builder()
                       .startAt(notifyAt)
                       .title(title)
                       .writer(writer)
                       .scheduleType(ScheduleType.NOTIFICATION)
                       .build();
    }

    public Task toTask() {
        return new Task(this);
    }

    public Event toEvent() {
        return new Event(this);
    }

    public Notification toNotification() {
        return new Notification(this);
    }
}
