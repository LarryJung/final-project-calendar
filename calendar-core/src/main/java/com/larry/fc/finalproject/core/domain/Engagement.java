package com.larry.fc.finalproject.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class Engagement {

    private Long id;
    private Event event;
    private User attendee;
    private RequestStatus status;
    private LocalDate createdAt;
}
