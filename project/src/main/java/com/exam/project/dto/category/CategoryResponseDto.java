package com.exam.project.dto.category;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private int status;
    private String message;
    @Nullable
    private Object data;

    public CategoryResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
