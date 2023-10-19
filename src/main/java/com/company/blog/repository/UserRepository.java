package com.company.blog.repository;

import com.company.blog.domain.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> searchByUsernameOrEmailIgnoreCase(String username, String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
    @Transactional
    Optional<UserEntity> deleteByUsernameIgnoreCase(String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameIgnoreCase(String username);
}
