package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Larry
 */
@Profile("test")
@Service
public class FakeEmailService implements EmailService {

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println(stuff.getProps());
    }
}
