package com.example.msjob.repository;

import com.example.msjob.dao.response.JobResponse;
import com.example.msjob.entity.JobEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long>, JpaSpecificationExecutor<JobEntity> {
    @Query("SELECT j FROM JobEntity j WHERE "+
            "j.title LIKE CONCAT('%',:keyword, '%') OR " +
            "j.description LIKE CONCAT('%',:keyword, '%')")
    List<JobEntity> searchJobEntities(String keyword);


    @Modifying
    @Query("UPDATE JobEntity j SET j.applicantsCount = j.applicantsCount+1 WHERE j.id = :jobId")
    void incrementApplicationCount(Long jobId);


}
