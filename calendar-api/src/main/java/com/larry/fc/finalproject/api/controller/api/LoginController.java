package com.larry.fc.finalproject.api.controller.api;

import com.larry.fc.finalproject.api.dto.LoginReq;
import com.larry.fc.finalproject.api.dto.SignUpReq;
import com.larry.fc.finalproject.api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> login(@Valid @RequestBody SignUpReq signUpReq, HttpSession httpSession) {
        loginService.signUp(signUpReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginReq loginReq, HttpSession httpSession) {
        loginService.login(loginReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        loginService.logout(httpSession);
        return ResponseEntity.ok().build();
    }
}

