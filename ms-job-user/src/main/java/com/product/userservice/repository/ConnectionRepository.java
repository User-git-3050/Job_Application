package com.product.userservice.repository;

import com.product.userservice.entity.ConnectionRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionRequestEntity,Long> {
}
