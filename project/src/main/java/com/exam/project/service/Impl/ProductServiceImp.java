package com.exam.project.service.Impl;

import com.exam.project.dto.product.ProductRequestDto;
import com.exam.project.entity.Category;
import com.exam.project.entity.Product;
import com.exam.project.exceptions.CategoryNotFoundException;
import com.exam.project.exceptions.ProductDataIntegrityViolationException;
import com.exam.project.exceptions.ProductNotFoundException;
import com.exam.project.repository.CategoryRepository;
import com.exam.project.repository.ProductRepository;
import com.exam.project.service.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new ProductNotFoundException("Category Not found"));

        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setCategory(category);
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());

        if(productRepository.existsByTitle(product.getTitle())) {
            throw new ProductDataIntegrityViolationException("Product title must be unique: "+ product.getTitle());
        }

        if(product.getPositionOrder() == null) {
            Integer maxPosition = productRepository.findMaxPositionOrder();
            product.setPositionOrder(maxPosition+1);
        }
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    public void deleteProduct(long id) {
        if(!this.productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product Not Found with id " + id);
        }
        productRepository.deleteById(id);    }

    public Product updateProduct(long id, ProductRequestDto newProductDto) {
        Product product = getProductById(id);
        product.setTitle(newProductDto.getTitle());
        product.setDescription(newProductDto.getDescription());
        product.setPrice(newProductDto.getPrice());
        product.setCategory(categoryRepository.findById(newProductDto.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category with id not found."+id)));
        return productRepository.save(product);
    }

    @Transactional
    public void updateProductOrder(List<Long> productIds) {
        int sortOrder = 1;
        for (Long productId : productIds) {
            Product product = getProductById(productId);
            product.setPositionOrder(sortOrder++);
            productRepository.save(product);
        }
    }

    public List<Product> getProductsByCategoryTitle(String categoryTitle) {
        return productRepository.findByCategoryTitle(categoryTitle);
    }

}
