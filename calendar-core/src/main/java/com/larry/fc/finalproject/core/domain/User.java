package com.larry.fc.finalproject.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDate createdAt;
}
