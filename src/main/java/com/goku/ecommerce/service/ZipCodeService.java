package com.goku.ecommerce.service;

import com.goku.ecommerce.domain.vo.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zipCodeService", url = "http://viazipCode.com.br/ws")
public interface ZipCodeService {

    @RequestMapping("/{zipCode}/json")
    Address getAddressByZipCode(@PathVariable("zipCode") String zipCode);
}
