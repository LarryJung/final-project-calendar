package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.core.domain.entity.Engagement;
import org.springframework.stereotype.Service;

/**
 * @author Larry
 */
@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(Engagement e) {
        System.out.println("메일발송 - attendee: " + e.getAttendee().getEmail() + ", scheduleId: " + e.getEvent().getId());
    }
}
