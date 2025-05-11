package com.product.userservice.entity;

import com.product.userservice.enums.ConnectionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "connection_requests")
public class ConnectionRequestEntity {
    @Id
    @SequenceGenerator(name = "connection_request_id",sequenceName = "connection_request_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "connection_request_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserEntity requester;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private UserEntity recipient;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    private LocalDateTime requestedAt;


}
