package com.example.spring_project.exception;

public class CarNotFoundError extends RuntimeException {
    public CarNotFoundError(String message) {
        super(message);
    }
}
