package com.moruflix.commons.config;

public enum ErrorCode implements IErrorCode {
    INVALID_FILTERS(Constants.INVALID_FILTERS),
    INVALID_PAGE(Constants.INVALID_PAGE),
    INVALID_SIZE(Constants.INVALID_SIZE),
    INVALID_PARAMS(Constants.INVALID_PARAMS);

    ErrorCode(String value) {
    }

    public static class Constants {
        public static final String INVALID_FILTERS = "INVALID_FILTERS";
        public static final String INVALID_PAGE = "INVALID_PAGE";
        public static final String INVALID_SIZE = "INVALID_SIZE";
        public static final String INVALID_PARAMS = "INVALID_PARAMS";
    }
}