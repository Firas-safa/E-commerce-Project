package com.exam.project.dto.product;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private int status;
    private String message;
    @Nullable
    private Object data;

    public ProductResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
