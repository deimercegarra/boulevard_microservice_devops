package com.pragma.boulevard_microservice_devops.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

}
