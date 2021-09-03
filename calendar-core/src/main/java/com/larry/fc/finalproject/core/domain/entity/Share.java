package com.larry.fc.finalproject.core.domain.entity;

import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.type.RequestReplyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "shares")
@NoArgsConstructor
@AllArgsConstructor
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUserId;
    private Long toUserId;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;
    @Enumerated(value = EnumType.STRING)
    private Direction direction; // From 이 To 에게 요청. 단방향인 경우는 To 의 캘린더가 From 에 보인다.
    private LocalDateTime createdAt;

    public Share reply(RequestReplyType type) {
        switch (type) {
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.requestStatus = RequestStatus.REJECTED;
                break;
        }
        return this;
    }

    public enum Direction {
        BI_DIRECTION, UNI_DIRECTION
    }
}
