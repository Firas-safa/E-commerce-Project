package com.exam.project.service;

import com.exam.project.dto.product.ProductRequestDto;
import com.exam.project.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(long id);

    Product addProduct(ProductRequestDto productRequestDto);

    Product updateProduct(long id, ProductRequestDto productRequestDto);

    void deleteProduct(long id);

    void updateProductOrder(List<Long> productIds);

    List<Product> getProductsByCategoryTitle(String categoryTitle);
}
