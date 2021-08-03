package com.larry.fc.finalproject.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Larry
 */
@Data
public class LoginReq {
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
