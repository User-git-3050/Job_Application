package com.product.userservice.repository;

import com.product.userservice.entity.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity,Long> {

}
