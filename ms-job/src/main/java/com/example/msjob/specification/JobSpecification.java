package com.example.msjob.specification;

import com.example.msjob.dao.request.JobSearchCriteria;
import com.example.msjob.entity.JobEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class JobSpecification implements Specification<JobEntity> {

    private final JobSearchCriteria criteria;


    public JobSpecification(JobSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<JobEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getTitle() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + criteria.getTitle() + "%"));
        }

        if (criteria.getDescription() != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + criteria.getDescription() + "%"));
        }

        if (criteria.getJobType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), criteria.getJobType()));
        }

        if (criteria.getExperienceLevel() != null) {
            predicates.add(criteriaBuilder.like(root.get("experienceLevel"), "%" + criteria.getExperienceLevel() + "%"));
        }

        if (criteria.getApplicationDeadLine() != null) {
            predicates.add(criteriaBuilder.equal(root.get("applicationDeadline"),  criteria.getApplicationDeadLine()));
        }

        if (criteria.getLocation() != null) {
            predicates.add(criteriaBuilder.like(root.get("location"), "%" + criteria.getLocation() + "%"));
        }

        if (criteria.getMinSalary() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minSalary"),  criteria.getMinSalary()));
        }

        if (criteria.getMaxSalary() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxSalary"), criteria.getMaxSalary()));
        }

        if (criteria.getPostedDate() != null) {
            predicates.add(criteriaBuilder.equal(root.get("postedDate"), criteria.getPostedDate()));
        }

        if (criteria.getCompany() != null) {
            predicates.add(criteriaBuilder.equal(root.get("company").get("name"), "%" + criteria.getCompany() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


    }
}
