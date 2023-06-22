package com.tBookStoreApp.tBookStoreApp.api;

import com.tBookStoreApp.tBookStoreApp.exception2.ApiErrorResponse;
import com.tBookStoreApp.tBookStoreApp.exception2.ResourceMappingException;
import com.tBookStoreApp.tBookStoreApp.exception2.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class BaseProductControllerAdvice {

    private final MessageSource messageSource;

    @Autowired
    public BaseProductControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    protected ApiErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex , Locale locale){
        Object[] args = ex.getArgs();
        log.error("Resource with id ~ '{}' could not be found", args[0]);

        MessageSourceResolvable resolvable = createErrorMessage(ex.getMessage(),args);
        return createErrorResponse(resolvable,HttpStatus.NOT_FOUND,locale);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ResourceMappingException.class})
    protected ApiErrorResponse handleResourceMappingException(ResourceMappingException ex, Locale locale){
        Object[] args = ex.getArgs();
        log.error("Resource with id ~ '{}' could not be mapped", args[0]);

        MessageSourceResolvable resolvable = createErrorMessage(ex.getMessage(),ex.getArgs());
        return createErrorResponse(resolvable,HttpStatus.UNPROCESSABLE_ENTITY,locale);
    }

    private ApiErrorResponse createErrorResponse(MessageSourceResolvable resolvable, HttpStatus status, Locale locale){
        String message = messageSource.getMessage(resolvable,locale);

        return ApiErrorResponse.builder().status(status).message(message).timeStamp(LocalDateTime.now()).build();
    }

    private DefaultMessageSourceResolvable createErrorMessage(String message, Object... params){
        return new DefaultMessageSourceResolvable(new String[]{message}, params);
    }
}
