package com.product.userservice.repository;

import com.product.userservice.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillEntity,Long> {
    Optional<List<SkillEntity>> findAllByNameIn(List<String> name);
    Optional<SkillEntity> findByName(String name);
}
