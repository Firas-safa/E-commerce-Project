package com.exam.project.exceptions;

public class CategoryDataIntegrityViolationException extends RuntimeException {
    public CategoryDataIntegrityViolationException(String message) {
        super(message);
    }
}
