package com.product.userservice.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectionRequest {
    private String requesterName;
    private String recipientId;
}
