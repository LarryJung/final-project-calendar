package com.larry.fc.finalproject.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class Event {

    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;
    private User writer;
    private List<Engagement> engagements;
    private LocalDate createdAt;
}
