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
public class ForListTaskDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.TASK;
    }
}
