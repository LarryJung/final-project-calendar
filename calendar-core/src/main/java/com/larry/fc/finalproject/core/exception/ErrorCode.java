package com.larry.fc.finalproject.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PASSWORD_NOT_MATCH("비밀번호 불일치"),
    ALREADY_EXISTS_USER("이미 있는 계정"),
    USER_NOT_FOUND("존재하지 않는 계정"),
    VALIDATION_FAIL("값이 유효하지 않음"),
    BAD_REQUEST("잘못된 접근"),
    EVENT_CREATE_OVERLAPPED_PERIOD("이벤트 기간 중복");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
