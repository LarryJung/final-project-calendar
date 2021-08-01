package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.EventCreateReq;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Larry
 */
@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;
    private final EmailService emailService;

    @Transactional
    public void create(EventCreateReq req, AuthUser authUser) {
        // attendees 의 스케쥴 시간과 겹치지 않는지?
        final List<Engagement> engagementList =
                engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(req.getAttendeeIds(),
                        req.getStartAt());
        if (engagementList
                .stream()
                .anyMatch(e -> e.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
                        && e.getStatus() == RequestStatus.ACCEPTED)) {
            throw new RuntimeException("cannot make engagement. period overlapped.");
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(eventSchedule);
        req.getAttendeeIds().stream()
                .map(userService::getOrThrowById)
                .forEach(user -> {
                    final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
                    emailService.sendEngagement(e);
                });
    }

}
