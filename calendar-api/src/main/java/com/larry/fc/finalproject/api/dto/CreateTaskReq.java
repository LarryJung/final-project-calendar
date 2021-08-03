package com.larry.fc.finalproject.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Larry
 */
@Data
public class CreateTaskReq {
    @NotBlank
    private final String title;
    private final String description;
    @NotNull
    private final LocalDateTime taskAt;
}
