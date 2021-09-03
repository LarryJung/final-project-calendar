package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.controller.api.BatchController;
import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import com.larry.fc.finalproject.core.domain.entity.Share;

/**
 * @author Larry
 */
public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
    void sendShareRequestMail(String email, String email1, Share.Direction direction);
}
