package com.goku.ecommerce.service.impl;


import com.goku.ecommerce.domain.exception.UserNotFoundException;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.security.HashUtil;
import com.goku.ecommerce.service.SessionService;
import com.goku.ecommerce.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.goku.ecommerce.security.JWTUtil.createJwt;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private UserService userService;

    @SneakyThrows
    public String loginUser(String username, String password) {
        String encryptedPassword = HashUtil.encryptPassword(password);
        User user = userService.checkUserLogin(username, encryptedPassword);

        return createJwt(user.getUsername());
    }

    public void logoffUser(String authorization) {
    }

}
