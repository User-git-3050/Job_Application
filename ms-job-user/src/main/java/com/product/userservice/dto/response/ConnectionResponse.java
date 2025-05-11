package com.product.userservice.dto.response;

import com.product.userservice.enums.ConnectionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ConnectionResponse {
    private Long id;
    private UserResponse requester;
    private UserResponse recipient;
    private ConnectionStatus connectionStatus;
    private LocalDateTime requestedAt;

}
