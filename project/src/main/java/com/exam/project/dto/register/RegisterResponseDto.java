package com.exam.project.dto.register;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {
    private int status;
    private String message;
    @Nullable
    private Object data;

    public RegisterResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
