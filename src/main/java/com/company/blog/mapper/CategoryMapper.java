package com.company.blog.mapper;

import com.company.blog.domain.CategoryEntity;
import com.company.blog.dto.category.CategoryDetailsDto;
import com.company.blog.dto.category.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(CategoryEntity category) {
        return CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .slug(category.getSlug())
                .build();
    }

    public static CategoryDetailsDto mapToCategoryDetailsDto(CategoryEntity category) {
        return CategoryDetailsDto.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .created(category.getCreated())
                .updated(category.getUpdated())
                .posts(category.getPosts().stream().map(PostMapper::mapToPostDto).collect(Collectors.toList()))
                .build();
    }

    public static CategoryEntity mapToCategory(CategoryDto categoryDto) {
        return CategoryEntity.builder()
                    .id(categoryDto.getId())
                    .name(categoryDto.getName())
                    .slug(categoryDto.getSlug())
                .build();
    }

}
