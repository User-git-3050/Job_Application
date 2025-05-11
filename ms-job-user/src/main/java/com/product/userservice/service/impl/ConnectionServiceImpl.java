package com.product.userservice.service.impl;

import com.product.userservice.dto.request.ConnectionRequest;
import com.product.userservice.dto.response.ConnectionResponse;
import com.product.userservice.entity.ConnectionRequestEntity;
import com.product.userservice.entity.UserEntity;
import com.product.userservice.enums.ConnectionStatus;
import com.product.userservice.exception.AlreadyExistsException;
import com.product.userservice.exception.NotFoundException;
import com.product.userservice.exception.UnauthorizedException;
import com.product.userservice.repository.ConnectionRepository;
import com.product.userservice.repository.UserRepository;
import com.product.userservice.service.inter.ConnectionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.product.userservice.enums.ErrorMessage.*;
import static com.product.userservice.enums.InfoMessage.*;
import static com.product.userservice.mapper.ConnectionMapper.CONNECTION_MAPPER;
import static java.lang.String.format;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ConnectionResponse> getAllConnectionRequests() {
        return List.of();
    }

    @Override
    public ConnectionResponse getConnectionRequestById(Long id) {
        return CONNECTION_MAPPER.mapToResponse(getConnectionRequestEntityById(id));
    }

    @Override
    public String sendConnectionRequest(Long recipientId, String requesterName) {
        UserEntity recipient = userRepository.findById(recipientId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_ID.getMessage(),
                                recipientId
                        )
                ));

        UserEntity requester = userRepository.findByUsername(requesterName)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_NAME.getMessage(),
                                requesterName
                        )
                ));

        if(recipient.getConnections().contains(requester)) {
            throw new AlreadyExistsException(CONNECTION_ALREADY_EXISTS.getMessage());
        }

        connectionRepository.save(CONNECTION_MAPPER.mapToEntity(recipient,requester));
        return CONNECTION_REQUEST_SENT.getMessage();
    }

    @Override
    public String acceptConnectionRequest(Long requestId,String recipientName) {
        ConnectionRequestEntity connectionRequestEntity = getConnectionRequestEntityById(requestId);

        UserEntity requester = connectionRequestEntity.getRequester();
        UserEntity recipient = connectionRequestEntity.getRecipient();

        if(hasPermission(recipientName , recipient) && connectionRequestEntity.getStatus() == ConnectionStatus.PENDING){
            connectionRequestEntity.setStatus(ConnectionStatus.ACCEPTED);
            connectionRepository.save(connectionRequestEntity);

            requester.getConnections().add(recipient);
            requester.setConnectionCount(requester.getConnectionCount()+1);

            recipient.getConnections().add(requester);
            recipient.setConnectionCount(recipient.getConnectionCount()+1);


            userRepository.save(requester);
            userRepository.save(recipient);

            return CONNECTION_REQUEST_ACCEPTED.getMessage();
        }

        throw new UnauthorizedException(NO_PERMISSION.getMessage());

    }

    @Override
    public String rejectConnectionRequest(Long requestId,String recipientName) {
        ConnectionRequestEntity connectionRequestEntity = getConnectionRequestEntityById(requestId);

        UserEntity recipient = connectionRequestEntity.getRecipient();

        if(hasPermission(recipientName , recipient) && connectionRequestEntity.getStatus() == ConnectionStatus.PENDING){
            connectionRequestEntity.setStatus(ConnectionStatus.REJECTED);
            connectionRepository.save(connectionRequestEntity);

            return CONNECTION_REQUEST_REJECTED.getMessage();
        }

        throw new UnauthorizedException(NO_PERMISSION.getMessage());

    }

    private Boolean hasPermission(String recipientName , UserEntity recipient){
        return recipient.getUsername().equals(recipientName);
    }

    private  ConnectionRequestEntity getConnectionRequestEntityById(Long requestId){
        return connectionRepository.findById(requestId)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                CONNECTION_REQUEST_NOT_FOUND_WITH_ID.getMessage(),
                                requestId
                        )
                ));
    }

}
