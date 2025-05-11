package com.product.userservice.service.inter;

import com.product.userservice.dto.request.ConnectionRequest;
import com.product.userservice.dto.response.ConnectionResponse;

import java.util.List;

public interface ConnectionService {
    List<ConnectionResponse> getAllConnectionRequests();

    ConnectionResponse getConnectionRequestById(Long id);

    String sendConnectionRequest(Long recipientId , String requesterName);

    String acceptConnectionRequest(Long requestId,String recipientName);

    String rejectConnectionRequest(Long requestId,String recipientName);
}
