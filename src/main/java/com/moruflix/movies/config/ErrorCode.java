package com.moruflix.movies.config;

import com.moruflix.commons.config.IErrorCode;

public enum ErrorCode implements IErrorCode {
    MOVIE_NOT_FOUND(Constants.MOVIE_NOT_FOUND),
    MOVIE_CONFLICT(Constants.MOVIE_CONFLICT),
    MOVIE_REPOSITORY_NOT_AVAILABLE(Constants.MOVIE_REPOSITORY_NOT_AVAILABLE),
    MOVIE_ALREADY_EXISTS(Constants.MOVIE_ALREADY_EXISTS),
    INVALID_USER_ID(Constants.INVALID_USER_ID),
    INVALID_TITLE(Constants.INVALID_TITLE),
    INVALID_RUNTIME(Constants.INVALID_RUNTIME),
    INVALID_RESUME(Constants.INVALID_RESUME),
    INVALID_DATE(Constants.INVALID_DATE),
    INVALID_POPULARITY(Constants.INVALID_POPULARITY);


    ErrorCode(String value) {
    }

    public static class Constants {
        public static final String MOVIE_NOT_FOUND = "MOVIE_NOT_FOUND";
        public static final String MOVIE_CONFLICT = "MOVIE_CONFLICT";
        public static final String MOVIE_REPOSITORY_NOT_AVAILABLE = "MOVIE_REPOSITORY_NOT_AVAILABLE";
        public static final String MOVIE_ALREADY_EXISTS = "MOVIE_ALREADY_EXISTS";
        public static final String INVALID_USER_ID = "INVALID_USER_ID";
        public static final String INVALID_TITLE = "INVALID_TITLE";
        public static final String INVALID_RUNTIME = "INVALID_RUNTIME";
        public static final String INVALID_RESUME = "INVALID_RESUME";
        public static final String INVALID_DATE = "INVALID_DATE";
        public static final String INVALID_POPULARITY = "INVALID_POPULARITY";

    }
}
