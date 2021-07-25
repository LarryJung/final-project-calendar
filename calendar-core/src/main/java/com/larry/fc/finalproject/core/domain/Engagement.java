package com.larry.fc.finalproject.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class Engagement {

    private Long id;
    private Event event;
    private User attendee;
    private RequestStatus status;
    private LocalDateTime createdAt;

    public Engagement(Long id, Event event, User attendee,
                      RequestStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.event = event;
        this.attendee = attendee;
        this.status = status;
        this.createdAt = createdAt;
    }
}
