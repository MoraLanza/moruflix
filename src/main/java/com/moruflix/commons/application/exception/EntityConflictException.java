package com.moruflix.commons.application.exception;

import com.moruflix.commons.config.IErrorCode;

public class EntityConflictException extends ErrorCodeException {

    public EntityConflictException(IErrorCode errorCode) {
        super(errorCode);
    }
}
