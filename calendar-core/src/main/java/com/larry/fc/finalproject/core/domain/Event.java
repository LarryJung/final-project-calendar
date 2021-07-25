package com.larry.fc.finalproject.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime createdAt;

    public Event(Long id, LocalDateTime startAt, LocalDateTime endAt, String title,
                 String description, User writer,
                 List<Engagement> engagements, LocalDateTime createdAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.title = title;
        this.description = description;
        this.writer = writer;
        this.engagements = engagements;
        this.createdAt = createdAt;
    }

    public void addEngagement(Engagement engagement) {
        if (this.getEngagements() == null) {
            this.engagements = new ArrayList<>();
        }
        this.engagements.add(engagement);
    }
}
