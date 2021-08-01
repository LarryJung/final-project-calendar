package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.core.domain.entity.Engagement;

/**
 * @author Larry
 */
public interface EmailService {
    void sendEngagement(Engagement e);
}
