package com.company.blog.service;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.dto.category.CategoryDetailsDto;
import com.company.blog.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<CategoryDto> getAllCategories(int pageNumber, int pageSize);
    CategoryDetailsDto getCategoryBySlug(String categorySlug);
    List<CategoryDto> searchCategory(String name);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, String categorySlug);
    void deleteCategory(String categorySlug);
}
