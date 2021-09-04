package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                       .filter(s -> s.getToUserId()
                                     .equals(toUserId))
                       .filter(s -> s.getRequestStatus() == RequestStatus.REQUESTED)
                       .map(share -> share.reply(type))
                       .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST));
    }

    /**
     * 공유 대상 조회
     * <p>
     * 자신과 양방향 공유인 상대방 (자신이 to, from 둘다 가능)
     * 내가 공유 수신자(toUser)인 경우 (단방향)
     *
     * @param authUser
     * @return
     */

    @Transactional
    public List<Long> findSharedUserIdsByUser(AuthUser authUser) {
        return Stream.concat(
                shareRepository.findAllByBiDirection(
                        authUser.getId(),
                        RequestStatus.ACCEPTED,
                        Share.Direction.BI_DIRECTION
                )
                               .stream()
                               .map(s -> s.getToUserId().equals(authUser.getId()) ? s.getFromUserId() : s.getToUserId()),
                shareRepository.findAllByToUserIdAndRequestStatusAndDirection(authUser.getId(),
                                                                              RequestStatus.ACCEPTED,
                                                                              Share.Direction.UNI_DIRECTION
                )
                               .stream()
                               .map(Share::getFromUserId)
        )
                     .collect(Collectors.toList());
    }
}

