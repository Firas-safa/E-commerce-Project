package com.exam.project.exceptions;

import com.exam.project.dto.category.CategoryResponseDto;
import com.exam.project.dto.login.LoginResponseDto;
import com.exam.project.dto.product.ProductResponseDto;
import com.exam.project.dto.register.RegisterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        ProductResponseDto errorResponse = new ProductResponseDto(404,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        CategoryResponseDto errorResponse = new CategoryResponseDto(404,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductDataIntegrityViolationException.class)
    public ResponseEntity<?> handleProductDataIntegrityViolationException(ProductDataIntegrityViolationException ex) {
        ProductResponseDto errorResponse = new ProductResponseDto(409,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryDataIntegrityViolationException.class)
    public ResponseEntity<?> handleCategoryDataIntegrityViolationException(CategoryDataIntegrityViolationException ex) {
        CategoryResponseDto errorResponse = new CategoryResponseDto(409,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        RegisterResponseDto errorResponse = new RegisterResponseDto(409,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        LoginResponseDto errorResponse = new LoginResponseDto(401, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Map<String, String>> handleTokenExpiredJwtException(TokenExpiredException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "JWT token has expired");
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<?> handleUserIdNotFoundException(UserIdNotFoundException ex) {
        ProductResponseDto errorResponse = new ProductResponseDto(404,ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
