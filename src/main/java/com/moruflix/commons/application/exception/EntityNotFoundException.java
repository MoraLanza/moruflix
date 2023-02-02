package com.moruflix.commons.application.exception;

import com.moruflix.commons.config.IErrorCode;

public class EntityNotFoundException extends ErrorCodeException {

    public EntityNotFoundException(IErrorCode errorCode) {
        super(errorCode);
    }
}