package com.larry.fc.finalproject.api.dto;

import lombok.Getter;

/**
 * @author Larry
 */
@Getter
public class AuthUser {
    private final Long id;

    private AuthUser(Long id) {
        this.id = id;
    }

    public static AuthUser of(Long id) {
        return new AuthUser(id);
    }
}

