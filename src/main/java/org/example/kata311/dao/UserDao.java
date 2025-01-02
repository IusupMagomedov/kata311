package org.example.kata311.dao;

import java.util.List;

import org.example.kata311.models.User;

public interface UserDao {
    List<User> findAll();

    User findById(Long id);

    Long save(User user);

    void update(User user);

    void delete(Long user);
}
