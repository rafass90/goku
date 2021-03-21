package com.goku.ecommerce.controller;

import com.goku.ecommerce.domain.vo.Address;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/users/{username}/address")
public class AddressController {

    private UserService service;

    @GetMapping
    public ResponseEntity<Address> findAddress(@RequestParam String username) throws NotFoundException {
        User user = service.findUserByUsername(username);
        return new ResponseEntity<Address>(user.getAddress(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> updateAddress(@RequestParam String username, @Valid @RequestBody Address address) throws NotFoundException {
        User user = service.updateUser(User.builder().username(username).address(address).build());
        return new ResponseEntity<Address>(user.getAddress(), HttpStatus.OK);
    }
}
