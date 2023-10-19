package com.company.blog.service.implementation;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.dto.category.CategoryDetailsDto;
import com.company.blog.dto.category.CategoryDto;
import com.company.blog.exception.CategoryNotFoundException;
import com.company.blog.mapper.CategoryMapper;
import com.company.blog.repository.CategoryRepository;
import com.company.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.company.blog.utils.SlugGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.blog.mapper.CategoryMapper.*;
import static com.company.blog.utils.SlugGenerator.toSlug;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CategoryEntity> categories = categoryRepository.findAll(pageable);
        List<CategoryEntity> listOfCategories = categories.getContent();
        return listOfCategories.stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDetailsDto getCategoryBySlug(String categorySlug) {
        CategoryEntity category = categoryRepository
                .findBySlug(categorySlug)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Category not be find by slug: " + categorySlug
                        )
                );

        return mapToCategoryDetailsDto(category);
    }

    @Override
    public List<CategoryDto> searchCategory(String name) {
        List<CategoryEntity> categories = categoryRepository.searchByNameIgnoreCase(name);
        return categories.stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity category = mapToCategory(categoryDto);
        category.setSlug(toSlug(categoryDto.getName()));
        CategoryEntity createdCategory = categoryRepository.save(category);
        return mapToCategoryDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categorySlug) {
        CategoryEntity category = categoryRepository
                .findBySlug(categorySlug)
                .orElseThrow(
                        () -> new CategoryNotFoundException(
                                "Category can not be update category by slug: " + categorySlug
                        )
                );
        category.setName(categoryDto.getName());
        category.setSlug(categoryDto.getSlug());
        CategoryEntity updatedCategory = categoryRepository.save(category);
        return mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(String categorySlug) {
        if (!categoryRepository.existsBySlug(categorySlug)) {
            throw new CategoryNotFoundException("Can not delete category with current slug: " + categorySlug);
        }
        categoryRepository.deleteBySlug(categorySlug);
    }
}
