package com.product.userservice.entity;

import com.product.userservice.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_id", sequenceName = "user_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Long connectionCount;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name ="userProfile_id", referencedColumnName ="id")
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_connections",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "connected_user_id")
    )
    private Set<UserEntity> connections;

    @OneToMany(mappedBy = "requester")
    private Set<ConnectionRequestEntity> sentRequests;

    @OneToMany(mappedBy = "recipient")
    private Set<ConnectionRequestEntity> receivedRequests;

}
