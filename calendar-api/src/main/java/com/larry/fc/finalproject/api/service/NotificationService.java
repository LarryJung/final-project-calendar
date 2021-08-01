package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.CreateNotificationReq;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Larry
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(CreateNotificationReq req, AuthUser authUser) {
        final User writer = userService.getOrThrowById(authUser.getId());
        req.getRepeatTimes()
                .forEach(notifyAt ->
                        scheduleRepository.save(Schedule.notification(req.getTitle(), notifyAt, writer)));
    }
}
