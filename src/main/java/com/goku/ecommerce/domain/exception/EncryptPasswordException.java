package com.goku.ecommerce.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EncryptPasswordException extends HttpClientErrorException {
    public EncryptPasswordException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
