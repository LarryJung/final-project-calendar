package com.larry.fc.finalproject.core.domain.entity.repository;

import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    List<Engagement> findAllByAttendeeIdInAndSchedule_EndAtAfter(List<Long> attendeeIds, LocalDateTime startAt);
}
