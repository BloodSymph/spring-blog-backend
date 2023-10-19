package com.company.blog.repository;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.domain.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByCategorySlug(String categorySlug);
    List<PostEntity> searchByTitleIgnoreCase(String title);
    Optional<PostEntity> findBySlug(String postSlug);
    @Transactional
    Optional<PostEntity> deleteBySlug(String postSlug);
    boolean existsBySlug(String postSlug);
}
