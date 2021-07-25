package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.entity.Schedule;

public class Task {

    private Schedule schedule;

    public Task (Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
