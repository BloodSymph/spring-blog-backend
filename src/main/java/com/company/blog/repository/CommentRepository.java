package com.company.blog.repository;

import com.company.blog.domain.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> searchByMessageIgnoreCase(String message);
    List<CommentEntity> findByPostSlug(String postSlug);
    Optional<CommentEntity> findBySlug(String commentSlug);
    @Transactional
    Optional<CommentEntity> deleteBySlug(String commentSlug);
    boolean existsBySlug(String commentSlug);
}
