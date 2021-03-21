package com.goku.ecommerce.controller;

import com.goku.ecommerce.domain.vo.Address;
import com.goku.ecommerce.service.ZipCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class ZipCodeController {

    @Autowired
    private ZipCodeService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/addresses/{cep}")
    public ResponseEntity<Address> findCep(@PathVariable String cep) {
        return new ResponseEntity<Address>(service.getAddressByZipCode(cep), HttpStatus.OK);
    }
}
