package com.practice.java.security.service;

import com.practice.java.security.entities.User;
import com.practice.java.security.interfaces.IUserService;
import com.practice.java.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User userSave(User user) {
        User userOpt = Optional.of(repository.save(user)).orElse(null);
        return userOpt;
    }

    @Override
    public User userUptade(User user) {
        User userOpt = Optional.of(repository.save(user)).orElse(null);
        return userOpt;
    }

    @Override
    public boolean userRemove(Integer id, User user) {
        try {
            repository.delete(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByPassword(String password) {
        return repository.findByPassword(password).orElse(null);
    }

    @Override
    public boolean userExists(String username) {
        return repository.existsByUsername(username).orElse(true);
    }
}
