package com.larry.fc.finalproject.batch.job;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Larry
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendMailBatchReq {
    private Long id;
    private LocalDateTime startAt;
    private String title;
    private String userEmail;
}
