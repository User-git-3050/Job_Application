package com.example.msjob.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class CompanyEntity {
    @Id
    @SequenceGenerator(sequenceName = "company_id", name = "company_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "company_id")
    private Long id;
    private String name;
    private String about;
    private String details;
    private String address;
    private Long ownerId;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<JobEntity> jobEntities;


}
