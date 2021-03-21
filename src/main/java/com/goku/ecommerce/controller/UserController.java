package com.goku.ecommerce.controller;

import com.goku.ecommerce.domain.dto.UserUpdateDTO;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.security.HashUtil;
import com.goku.ecommerce.service.SessionService;
import com.goku.ecommerce.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static com.goku.ecommerce.security.JWTUtil.validateUsername;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody User user) {
        service.saveUser(user);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<Void> editUser(@RequestHeader String authorization, @Valid @RequestBody UserUpdateDTO userDTO) {
        validateUsername(authorization, userDTO.getUsername());

        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();

        service.updateUser(user);
        return ResponseEntity.accepted().build();
    }
}
