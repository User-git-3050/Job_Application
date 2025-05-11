package com.example.msjob.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "job_skill")
public class JobSkillEntity {
    @Id
    @SequenceGenerator(name = "job_skill_id", sequenceName = "job_skill_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_skill_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "job_id")
    private JobEntity job;

    private Long skillId;
}
