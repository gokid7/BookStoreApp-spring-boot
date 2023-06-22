package com.tBookStoreApp.tBookStoreApp.exception2;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.util.Locale;
import java.util.UUID;

@Getter
public abstract class AbstractBaseException extends RuntimeException{
    private final String key;
    private final Locale locale;
    private final Object[] args;


    protected AbstractBaseException(String key) {
        this(key,null, Locale.getDefault());
    }
    protected AbstractBaseException(String key, long id){
        this(key, new Object[]{id}, Locale.getDefault());
    }
    protected AbstractBaseException(String key , String tDomainId){
        this(key,new Object[]{tDomainId},Locale.getDefault());
    }

    protected AbstractBaseException(String key, Object[] objects, Locale aDefault) {
        this.key = key;
        this.args = objects;
        this.locale = aDefault;
    }
}
