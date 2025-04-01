package com.exam.project.controller;

import com.exam.project.dto.product.ProductRequestDto;
import com.exam.project.dto.product.ProductResponseDto;
import com.exam.project.entity.Product;
import com.exam.project.service.Impl.ProductServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Validated
public class ProductController {

    private final ProductServiceImp productService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ProductResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return ProductResponseDto.builder()
                .status(200)
                .data(products)
                .message("All Products Retrieved Successfully")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ProductResponseDto createProduct(@RequestBody @Valid ProductRequestDto productDto) {
        Product productSaved = productService.addProduct(productDto);
        return  ProductResponseDto.builder()
                .data(productSaved)
                .message("Product Added Successfully")
                .status(201)
                .build();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        return ProductResponseDto.builder()
                .status(200)
                .message("Product with id "+ id + " was found successfully")
                .data(product)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,@RequestBody @Valid ProductRequestDto productDto) {
        Product updatedProduct = productService.updateProduct(id,productDto);
        return ProductResponseDto.builder()
                .status(200)
                .message("Product Updated Successfully")
                .data(updatedProduct)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ProductResponseDto deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ProductResponseDto.builder()
                .status(200)
                .message("Product with id "+ id +" was deleted successfully")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update-order")
    public void updateProductOrder(@RequestBody List<Long> productIds) {
        productService.updateProductOrder(productIds);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/category/{categoryTitle}")
    public ProductResponseDto getProductsByCategory(@PathVariable String categoryTitle) {
        List<Product> products = productService.getProductsByCategoryTitle(categoryTitle);
        return ProductResponseDto.builder()
                .status(200)
                .data(products)
                .message("All Products for Category Retrieved Successfully")
                .build();
    }

}
