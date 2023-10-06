package com.example.wanted.common.exception.runtime;

import org.springframework.http.HttpStatus;

public abstract class BaseRuntimeException extends RuntimeException {
    public abstract String getMessage();
    public abstract HttpStatus getStatus();
}
