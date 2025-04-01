package com.exam.project.exceptions;

public class ProductDataIntegrityViolationException extends RuntimeException {
    public ProductDataIntegrityViolationException(String message) {
        super(message);
    }
}
