package com.product.userservice.controller;

import com.product.userservice.dto.request.ConnectionRequest;
import com.product.userservice.dto.response.ConnectionResponse;
import com.product.userservice.service.inter.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/connection/")
public class ConnectionController {

    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping
    public ResponseEntity<List<ConnectionResponse>> getAllConnectionRequests() {
        return ResponseEntity.ok(connectionService.getAllConnectionRequests());
    }

    @GetMapping("{id}")
    public ResponseEntity<ConnectionResponse> getConnectionRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(connectionService.getConnectionRequestById(id));
    }

    @PostMapping("request/{recipientId}")
    public ResponseEntity<String> sendConnectionRequest(@PathVariable Long recipientId,@RequestHeader String username) {
        return ResponseEntity.ok(connectionService.sendConnectionRequest(recipientId, username));
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptConnectionRequest(@PathVariable Long requestId,@RequestHeader String username) {
        return ResponseEntity.ok(connectionService.acceptConnectionRequest(requestId, username));
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectConnectionRequest(@PathVariable Long requestId, @RequestHeader String username) {
        return ResponseEntity.ok(connectionService.rejectConnectionRequest(requestId, username));
    }

}
