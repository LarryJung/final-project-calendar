package com.larry.fc.finalproject.core.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Larry
 */
@Data
public class UserCreateReq {
    private final String name;
    private final String email;
    private final String password;
    private final LocalDate birthday;
}
