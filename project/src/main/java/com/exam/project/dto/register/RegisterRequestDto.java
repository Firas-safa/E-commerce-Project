package com.exam.project.dto.register;

import com.exam.project.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "UserName must be filled")
    private String userName;

    @NotBlank(message = "FullName must be filled")
    private String fullName;

    @NotBlank(message = "Password must be filled")
    @Size(min = 8, message = "Password  must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter,one digit and a special character"
    )
    private String password;

    @NotNull(message = "Roles cannot be null")
    @Size(min = 1, message = "At least one role must be assigned")
    private Set<Role> roles;
}
