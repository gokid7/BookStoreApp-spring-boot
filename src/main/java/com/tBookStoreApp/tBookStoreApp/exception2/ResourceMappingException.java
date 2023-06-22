package com.tBookStoreApp.tBookStoreApp.exception2;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Getter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceMappingException extends AbstractBaseException{

    public ResourceMappingException(String key, long id) {
        super(key, id);
    }
}
