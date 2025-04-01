package com.exam.project.controller;

import com.exam.project.dto.category.CategoryRequestDto;
import com.exam.project.dto.category.CategoryResponseDto;
import com.exam.project.entity.Category;
import com.exam.project.service.Impl.CategoryServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
@Validated
public class CategoryController {

    private final CategoryServiceImp categoryService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping()
    public CategoryResponseDto getAllCategories() {
        List<Category> products = categoryService.getAllCategories();
        return CategoryResponseDto.builder()
                .status(200)
                .data(products)
                .message("All Products Retrieved Successfully")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public CategoryResponseDto createCategory(@RequestBody @Valid CategoryRequestDto categoryDto) {
        Category categorySaved = categoryService.addCategory(categoryDto);
        return CategoryResponseDto.builder()
                .data(categorySaved)
                .status(201)
                .message("Category Added Successfully")
                .build();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return CategoryResponseDto.builder()
                .status(200)
                .message("Category with id "+ id + " was found successfully")
                .data(category)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id,@RequestBody @Valid CategoryRequestDto categoryDto) {
        Category updatedCategory = this.categoryService.updateCategory(id, categoryDto);
        return CategoryResponseDto.builder()
                .status(200)
                .message("Category Updated Successfully")
                .data(updatedCategory)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public CategoryResponseDto deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return CategoryResponseDto.builder()
                .status(200)
                .message("Category with id "+ id +" was deleted successfully")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update-order")
    public void updateCategoryOrder(@RequestBody List<Long> categoryIds) {
        categoryService.updateCategoryOrder(categoryIds);
    }
}
