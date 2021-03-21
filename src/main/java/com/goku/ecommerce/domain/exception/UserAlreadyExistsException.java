package com.goku.ecommerce.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends HttpClientErrorException {
    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "User already exists");
    }
}
