package com.exam.project.controller;

import com.exam.project.dto.login.LoginRequestDto;
import com.exam.project.dto.login.LoginResponseDto;
import com.exam.project.dto.register.RegisterRequestDto;
import com.exam.project.dto.register.RegisterResponseDto;
import com.exam.project.entity.Users;
import com.exam.project.security.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/registerNewUser")
    public RegisterResponseDto registerNewUser(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        Users user = userService.registerNewUser(registerRequestDto);
        return RegisterResponseDto.builder()
                .status(201)
                .data(user)
                .message("User added Successfully")
                .build();
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto user) {
        return userService.authenticateUser(user);
    }
}