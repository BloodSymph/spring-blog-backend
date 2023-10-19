package com.company.blog.repository;

import com.company.blog.domain.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> searchByNameIgnoreCase(String name);
    Optional<CategoryEntity> findBySlug(String categorySlug);
    @Transactional
    Optional<CategoryEntity> deleteBySlug(String categorySlug);
    boolean existsBySlug(String categorySlug);
}
