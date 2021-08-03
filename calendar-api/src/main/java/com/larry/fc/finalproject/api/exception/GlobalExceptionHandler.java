package com.larry.fc.finalproject.api.exception;

import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static com.larry.fc.finalproject.api.exception.ErrorHttpStatusMapper.mapToStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CalendarException.class)
    public ResponseEntity<ErrorResponse> handle(
            CalendarException ex) {
        final ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode, errorCode.getMessage()),
                mapToStatus(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        final ErrorCode code = ErrorCode.VALIDATION_FAIL;
        return new ResponseEntity<>(new ErrorResponse(code,
                Optional.ofNullable(ex.getBindingResult()
                                .getFieldError())
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .orElse(code.getMessage())),
                mapToStatus(code));
    }

    @Data
    public static class ErrorResponse {
        private final ErrorCode errorCode;
        private final String errorMessage;
    }
}
