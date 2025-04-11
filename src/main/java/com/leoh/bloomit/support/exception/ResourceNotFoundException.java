package com.leoh.bloomit.support.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public ResourceNotFoundException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
    }
}
