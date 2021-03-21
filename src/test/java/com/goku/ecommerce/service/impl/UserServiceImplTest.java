package com.goku.ecommerce.service.impl;

import com.goku.ecommerce.domain.exception.BadRequestException;
import com.goku.ecommerce.domain.exception.UserAlreadyExistsException;
import com.goku.ecommerce.domain.exception.UserNotFoundException;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Test
    public void findUserByUsernameSuccessTests() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.of(User.builder().build()));

        User userByUsername = service.findUserByUsername("");
        assertNotNull(userByUsername);

    }

    @Test(expected = UserNotFoundException.class)
    public void findUserByUsernameNotFoundTests() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());

        User userByUsername = service.findUserByUsername("");
    }

    @Test
    public void checkUserLoginSuccessTests() {
        when(repository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(User.builder().build()));

        User userByUsername = service.checkUserLogin("", "");

        assertNotNull(userByUsername);
    }

    @Test(expected = UserNotFoundException.class)
    public void checkUserLoginNotFoundTests() {
        when(repository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        service.checkUserLogin("", "");
    }

    @SneakyThrows
    @Test
    public void saveUserSuccessTests(){
        String userPassword = "pwd";

        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(User.builder().build());

        User user = service.saveUser(User.builder().password(userPassword).build());

        assertNotNull(user);
        assertThat(user.getPassword(), is(not(userPassword)));
    }

    @SneakyThrows
    @Test(expected = UserAlreadyExistsException.class)
    public void saveUserRepeatedTests(){
        String username = "someUser";

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(User.builder().build()));

        service.saveUser(User.builder().username(username).build());

    }

    @SneakyThrows
    @Test
    public void updateUserTestsSuccess(){
        String oldPassword = "oldPwd";
        String newPassword = "newPwd";

        User mockUser = User.builder().name("newName").username("someUser").password(newPassword).build();
        User mockOldUser = User.builder().password(oldPassword).build();

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(mockOldUser));
        when(repository.save(any())).thenReturn(new User());

        User updatedUser = service.updateUser(mockUser);

        assertNotNull(updatedUser);

        assertThat(updatedUser.getPassword(), is(not(equalTo(oldPassword))));
        assertThat(updatedUser.getPassword(), is(not(equalTo(newPassword))));
    }

    @SneakyThrows
    @Test(expected = BadRequestException.class)
    public void updateUserAllFieldsEmptyTests(){
        User mockUser = User.builder().build();

        User updatedUser = service.updateUser(mockUser);

    }
}