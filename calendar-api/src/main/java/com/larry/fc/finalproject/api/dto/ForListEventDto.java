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
public class ForListEventDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.EVENT;
    }
}