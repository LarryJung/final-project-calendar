package com.larry.fc.finalproject.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Larry
 */
@Data
public class TaskCreateReq {
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;
}
