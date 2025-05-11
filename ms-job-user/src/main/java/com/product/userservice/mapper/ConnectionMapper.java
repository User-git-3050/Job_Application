package com.product.userservice.mapper;

import com.product.userservice.dto.response.ConnectionResponse;
import com.product.userservice.entity.ConnectionRequestEntity;
import com.product.userservice.entity.UserEntity;
import com.product.userservice.enums.ConnectionStatus;

import java.time.LocalDateTime;

import static com.product.userservice.mapper.UserEntityMapper.USER_ENTITY_MAPPER;

public enum ConnectionMapper {
    CONNECTION_MAPPER;

    public ConnectionRequestEntity mapToEntity(UserEntity recipient, UserEntity requester){
        return ConnectionRequestEntity.builder()
                .requester(requester)
                .recipient(recipient)
                .status(ConnectionStatus.PENDING)
                .requestedAt(LocalDateTime.now())
                .build();
    }

    public ConnectionResponse mapToResponse(ConnectionRequestEntity connectionRequestEntity){
        return ConnectionResponse.builder()
                .id(connectionRequestEntity.getId())
                .requester(USER_ENTITY_MAPPER.toUserResponse(connectionRequestEntity.getRequester()))
                .recipient(USER_ENTITY_MAPPER.toUserResponse(connectionRequestEntity.getRecipient()))
                .connectionStatus(connectionRequestEntity.getStatus())
                .requestedAt(connectionRequestEntity.getRequestedAt())
                .build();
    }
}
