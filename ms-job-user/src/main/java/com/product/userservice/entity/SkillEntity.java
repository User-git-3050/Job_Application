package com.product.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class SkillEntity {
    @Id
    @SequenceGenerator(sequenceName = "skill_id", name = "skill_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "skill_id")
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "skill_id"),
    inverseJoinColumns = @JoinColumn(name = "user_profile_id"))
    private List<UserProfile> userProfile;
}
