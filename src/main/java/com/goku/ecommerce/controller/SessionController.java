package com.goku.ecommerce.controller;

import com.goku.ecommerce.domain.dto.LoginDTO;
import com.goku.ecommerce.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class SessionController {

    @Autowired
    private SessionService service;

    @PostMapping(path = "/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDTO login) throws Exception {
        String token = service.loginUser(login.getUsername(), login.getPassword());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);

        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @DeleteMapping(path = "/logoff")
    public ResponseEntity<Void> logoff(@RequestHeader String authorization) {
        log.error("LOGOFF NOT IMPLEMENTED");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
