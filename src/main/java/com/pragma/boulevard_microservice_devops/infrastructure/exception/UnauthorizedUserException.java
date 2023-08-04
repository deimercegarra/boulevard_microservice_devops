package com.pragma.boulevard_microservice_devops.infrastructure.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super();
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
