package com.goku.ecommerce.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends HttpClientErrorException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
