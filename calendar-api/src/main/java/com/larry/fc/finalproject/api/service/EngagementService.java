package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.core.domain.type.RequestReplyType;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Larry
 */
@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(Engagement::isRequested)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }
}
