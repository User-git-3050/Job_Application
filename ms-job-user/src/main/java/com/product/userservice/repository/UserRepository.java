package com.product.userservice.repository;

import com.product.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    @Query("SELECT j FROM UserEntity j WHERE "+
            "j.name LIKE CONCAT('%',:keyword, '%') OR " +
            "j.username LIKE CONCAT('%',:keyword, '%') OR " +
            "j.surname LIKE CONCAT('%',:keyword, '%')")
    List<UserEntity> searchUserEntities(String keyword);
}
