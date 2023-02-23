package com.tBookStoreApp.tBookStoreApp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
        log.error(msg);
    }
}
