package com.tBookStoreApp.tBookStoreApp.exception2;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends AbstractBaseException{

    public ResourceNotFoundException(String key, long id) {
        super(key, id);
    }

    public ResourceNotFoundException(String key, String tDomainId) {
        super(key, tDomainId);
    }
}
