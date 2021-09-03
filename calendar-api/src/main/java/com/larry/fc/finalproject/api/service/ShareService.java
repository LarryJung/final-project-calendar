package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Share;
import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.ShareRepository;
import com.larry.fc.finalproject.core.domain.type.RequestReplyType;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final UserService userService;
    private final ShareRepository shareRepository;
    private final EmailService emailService;

    @Transactional
    public Share createShare(Long fromUserId, Long toUserId, Share.Direction direction) {
        final User toUser = userService.getOrThrowById(toUserId);
        final User fromUser = userService.getOrThrowById(fromUserId);
        emailService.sendShareRequestMail(toUser.getEmail(), fromUser.getEmail(), direction);
        return shareRepository.save(Share.builder()
                                         .fromUserId(fromUserId)
                                         .toUserId(toUserId)
                                         .direction(direction)
                                         .createdAt(LocalDateTime.now())
                                         .requestStatus(RequestStatus.REQUESTED)
                                         .build());
    }

    @Transactional
    public void replyToShareRequest(Long shareId, Long toUserId, RequestReplyType type) {
        shareRepository.findById(shareId)
                       .filter(s -> s.getToUserId().equals(toUserId))
                       .filter(s -> s.getRequestStatus() == RequestStatus.REQUESTED)
                       .map(share -> share.reply(type))
                       .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST));
    }
}

