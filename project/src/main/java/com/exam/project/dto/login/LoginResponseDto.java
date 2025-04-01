package com.exam.project.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    private int status;
    private String message;
    private AuthResponseDto data;

    public LoginResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
