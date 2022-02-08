package com.larry.fc.finalproject.api.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Larry
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final JavaMailSender emailSender;

    @GetMapping("/api/mail")
    public void sendMail() {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("ehtjd33@mail.mail");
            helper.setSubject("제목입니당~~");
            helper.setText("테슷흐 메일");
        };
        emailSender.send(preparator);
    }

}
