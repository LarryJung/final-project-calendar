package com.larry.fc.finalproject.core.exception;

import lombok.Getter;

@Getter
public class CalendarException extends RuntimeException {

    private final ErrorCode errorCode;

    public CalendarException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
