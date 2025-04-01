package com.exam.project.service.Impl;

import com.exam.project.dto.category.CategoryRequestDto;
import com.exam.project.entity.Category;
import com.exam.project.entity.Product;
import com.exam.project.exceptions.CategoryDataIntegrityViolationException;
import com.exam.project.exceptions.CategoryNotFoundException;
import com.exam.project.repository.CategoryRepository;
import com.exam.project.repository.ProductRepository;
import com.exam.project.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setTitle(categoryRequestDto.getTitle());
        category.setDescription(categoryRequestDto.getDescription());

        if (categoryRepository.existsByTitle(category.getTitle())) {
            throw new CategoryDataIntegrityViolationException("Category title must be unique: " + category.getTitle());
        }

        if (category.getPositionOrder() == null) {
            Integer maxPosition = categoryRepository.findMaxPositionOrder();
            category.setPositionOrder(maxPosition + 1);
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found with id: " + id));
    }

    public Category updateCategory(long id, CategoryRequestDto categoryDetails) {
        Category category = getCategoryById(id);
        category.setTitle(categoryDetails.getTitle());
        category.setDescription(categoryDetails.getDescription());
        category.setId(id);
        return categoryRepository.save(category);
    }

    public void deleteCategory(long id) {
        if (!this.categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category Not Found with id " + id);
        }
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            productRepository.deleteById(product.getId());
        }
        categoryRepository.deleteById(id);
    }

    public void updateCategoryOrder(List<Long> categoryIds) {
        int sortOrder = 1;
        for (Long categoryId : categoryIds) {
            Category category = getCategoryById(categoryId);
            category.setPositionOrder(sortOrder++);
            categoryRepository.save(category);
        }
    }
}
