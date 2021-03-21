package com.goku.ecommerce.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidRequestException extends HttpClientErrorException {
    public InvalidRequestException() {
        super(HttpStatus.FORBIDDEN, "Invalid request");
    }
}
