package com.product.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "user_profiles")
@AllArgsConstructor
@RequiredArgsConstructor
public class UserProfile {
    @Id
    @SequenceGenerator(name = "user_profile_id", sequenceName = "user_profile_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_id")
    private Long id;
    private String address;
    private Integer number;
    private Long currentCompanyId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userProfile")
    private Set<SkillEntity> skills;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "userProfile")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "userProfile")
    private Set<ExperienceEntity> experiences;


}
