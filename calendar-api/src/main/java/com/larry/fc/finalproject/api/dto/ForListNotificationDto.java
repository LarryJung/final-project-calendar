package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Larry
 */
@Builder
@Data
public class ForListNotificationDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final LocalDateTime notifyAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
