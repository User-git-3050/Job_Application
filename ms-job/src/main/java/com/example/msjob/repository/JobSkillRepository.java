package com.example.msjob.repository;

import com.example.msjob.entity.JobSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkillEntity,Long> {
    void deleteAllByJobId(Long jobId);

}
