package com.goku.ecommerce.service;

import com.goku.ecommerce.domain.vo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService {

    User saveUser(User user) throws Exception;

    User updateUser(User user) throws NotFoundException;

    @Cacheable(value = "user", key = "#username")
    User findUserByUsername(String username) throws NotFoundException;

    @Cacheable(value = "user", key = "#username")
    User checkUserLogin(String username, String password);
}
