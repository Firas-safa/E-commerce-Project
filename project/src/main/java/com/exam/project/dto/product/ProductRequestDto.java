package com.exam.project.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "Product title must be filled")
    private String title;

    @NotBlank(message = "Product description must be filled")
    private String description;

    @NotNull(message = "Product price must be filled")
    @Positive(message = "Product price must be positive")
    private double price;

    @NotNull(message = "Category Id must be not be empty")
    private Long categoryId;
}
