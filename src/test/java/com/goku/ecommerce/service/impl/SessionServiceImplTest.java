package com.goku.ecommerce.service.impl;

import com.goku.ecommerce.domain.exception.UserNotFoundException;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl service;

    @Mock
    private UserService userService;

    @Test
    public void LoginUserSuccessTests(){

        when(userService.checkUserLogin(anyString(), anyString())).thenReturn(User.builder().build());

        String jwt = service.loginUser("user", "pwd");

        assertThat(jwt).isNotBlank();

    }

    @Test(expected = UserNotFoundException.class)
    public void loginUserNotFoundTests(){

        when(userService.checkUserLogin(anyString(), anyString())).thenThrow(UserNotFoundException.class);

        String jwt = service.loginUser("user", "pwd");
    }

}