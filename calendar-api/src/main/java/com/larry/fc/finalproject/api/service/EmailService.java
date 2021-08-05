package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;

/**
 * @author Larry
 */
public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}
