package org.example.kata311.service;

import java.util.List;

import org.example.kata311.models.User;

public interface UserService {
    List<User> getUsers();

    List<User> getUsers(Integer limit);

    User getUser(Long id);

    void createUser(String name, String email, String password);

    void updateUser(Long id, String name, String email, String password);

    void deleteUser(Long id);
}
