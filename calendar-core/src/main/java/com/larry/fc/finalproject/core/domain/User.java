package com.larry.fc.finalproject.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDateTime createdAt;

    public User(Long id, String name, String email, String password, LocalDate birthday,
                LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }
}
