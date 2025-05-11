package com.product.userservice.repository.specification;

import com.product.userservice.dto.request.UserSearchCriteria;
import com.product.userservice.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class UserSpecification implements Specification<UserEntity> {

    private final UserSearchCriteria criteria;

    public UserSpecification(UserSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getUsername() != null) {
            predicates.add(criteriaBuilder.like(root.get("username"), "%s" + criteria.getUsername() + "%"));
        }

        if (criteria.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%s" + criteria.getName() + "%s"));
        }

        if (criteria.getSurname() != null) {
            predicates.add(criteriaBuilder.like(root.get("surname"), "%s" + criteria.getSurname() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
