package com.unidac.tools.exception;

import com.unidac.tools.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler (value = ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseDTO handleTokenRefreshException(ResourceAlreadyExistsException ex, WebRequest request) {
        return new ResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                null,
                Instant.now().toString(),
                request.getDescription(false)
        );
    }
}
