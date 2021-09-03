package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.type.RequestReplyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReplyReq {
    @NotNull
    private RequestReplyType type;
}
