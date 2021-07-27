package com.larry.fc.finalproject.api.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Larry
 */
@Data
public class SignUpReq {
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;
}
