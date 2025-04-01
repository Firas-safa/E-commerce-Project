package com.exam.project.service;


import com.exam.project.dto.category.CategoryRequestDto;
import com.exam.project.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(long id);
    Category addCategory(CategoryRequestDto categoryRequestDto);
    Category updateCategory(long id, CategoryRequestDto categoryRequestDto);
    void deleteCategory(long id);
    void updateCategoryOrder(List<Long> categoryIds);
}
