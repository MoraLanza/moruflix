package com.moruflix.commons.application.exception;

import com.moruflix.commons.config.IErrorCode;

public class RepositoryNotAvailableException extends ErrorCodeException {

    public RepositoryNotAvailableException(IErrorCode errorCode) {
        super(errorCode);
    }
}
