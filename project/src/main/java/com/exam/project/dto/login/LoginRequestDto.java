package com.exam.project.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "UserName must be filled")
    private String userName;

    @NotBlank(message = "Password must be filled")
    private String password;
}
