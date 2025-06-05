package com.practice.java.security.interfaces;

import com.practice.java.security.entities.User;

public interface IUserService {

    User userSave(User user);
    User userUptade(User user);
    boolean userRemove(Integer id, User user);

    User findByUsername(String username);
    User findByPassword(String password);

    boolean userExists(String username);
}
