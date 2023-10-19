package com.company.blog.controller;

import com.company.blog.dto.category.CategoryDetailsDto;
import com.company.blog.dto.category.CategoryDto;
import com.company.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/categories/{categorySlug}")
    public CategoryDetailsDto getCategory(@PathVariable(value = "categorySlug") String categorySlug) {
        return categoryService.getCategoryBySlug(categorySlug);
    }

    @GetMapping("/categories/search")
    public List<CategoryDto> searchCategoryByName(@RequestParam(value = "name") String name) {
        return categoryService.searchCategory(name);
    }
}
