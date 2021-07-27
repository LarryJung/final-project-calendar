package com.larry.fc.finalproject.api.dto;

import lombok.Data;

/**
 * @author Larry
 */
@Data
public class LoginReq {
    private final String email;
    private final String password;
}
