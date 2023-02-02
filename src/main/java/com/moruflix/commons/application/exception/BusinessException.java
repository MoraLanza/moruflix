package com.moruflix.commons.application.exception;

import com.moruflix.commons.config.IErrorCode;

public class BusinessException extends ErrorCodeException {

    public BusinessException(IErrorCode errorCode) {
        super(errorCode);
    }
}
