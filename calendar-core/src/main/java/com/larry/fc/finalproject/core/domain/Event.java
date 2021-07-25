package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.entity.Schedule;

public class Event {
    private Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
