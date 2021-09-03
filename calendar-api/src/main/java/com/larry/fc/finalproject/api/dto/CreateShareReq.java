package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.entity.Share;
import lombok.Data;

@Data
public class CreateShareReq {
    private final Long toUserId;
    private final Share.Direction direction;
}
