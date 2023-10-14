package com.valenteNews.newsApp.config.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {

    public EmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
