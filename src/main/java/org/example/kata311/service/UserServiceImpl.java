package org.example.kata311.service;

import org.example.kata311.dao.UserDao;
import org.example.kata311.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public List<User> getUsers(Integer limit) {
        return userDao
                .findAll()
                .stream()
                .limit(limit)
                .toList();
    }

    @Override
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public Long createUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}