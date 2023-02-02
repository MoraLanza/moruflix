package com.moruflix.commons.application.exception;

import com.moruflix.commons.config.IErrorCode;

public abstract class ErrorCodeException extends RuntimeException {

    private final IErrorCode errorCode;

    public ErrorCodeException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return this.errorCode;
    }
}
