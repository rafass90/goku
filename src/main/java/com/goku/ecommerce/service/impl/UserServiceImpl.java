package com.goku.ecommerce.service.impl;

import com.goku.ecommerce.domain.exception.BadRequestException;
import com.goku.ecommerce.domain.exception.UserAlreadyExistsException;
import com.goku.ecommerce.domain.exception.UserNotFoundException;
import com.goku.ecommerce.domain.vo.User;
import com.goku.ecommerce.repository.UserRepository;
import com.goku.ecommerce.security.HashUtil;
import com.goku.ecommerce.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @SneakyThrows
    public User findUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User checkUserLogin(String username, String password) {
        return repository.findByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User saveUser(User user) throws Exception {
        if (repository.findByUsername(user.getUsername()).isPresent())
            throw new UserAlreadyExistsException();

        user.setPassword(HashUtil.encryptPassword(user.getPassword()));
        return repository.save(user);
    }

    @SneakyThrows
    public User updateUser(User user) throws NotFoundException {
        if (Objects.isNull(user.getPassword()) && Objects.isNull(user.getPassword())
                && Objects.isNull(user.getAddress()))
            throw new BadRequestException("Fill some fields");

        User oldUser = findUserByUsername(user.getUsername());

        if (Objects.nonNull(user.getAddress()))
            oldUser.setAddress(user.getAddress());

        if (Objects.nonNull(user.getPassword())) {
            oldUser.setPassword(HashUtil.encryptPassword(user.getPassword()));
        }

        if (Objects.nonNull(user.getName()))
            oldUser.setName(user.getName());

        return repository.save(oldUser);
    }

}
