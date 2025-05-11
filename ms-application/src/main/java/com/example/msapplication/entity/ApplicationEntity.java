package com.example.msapplication.entity;

import com.example.msapplication.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ApplicationEntity {
    @Id
    @SequenceGenerator(name = "application_id" , sequenceName = "application_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "application_id")
    Long id;

    @Enumerated(EnumType.STRING)
    StatusEnum status;

    @CreationTimestamp
    LocalDateTime submissionDate;

    String resumePath;
    String coverLetter;
    Long userId;
    Long jobId;

}
