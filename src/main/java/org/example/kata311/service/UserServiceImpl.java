package org.example.kata311.service;

import org.example.kata311.dao.UserDao;
import org.example.kata311.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (limit != null) {
            return userDao
                    .findAll()
                    .stream()
                    .limit(limit)
                    .toList();
        }
        return userDao.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public void createUser(String name, String email, String password) {
        User user = new User(name, password, email);
        Long id = userDao.save(user);
        System.out.println("User has been created, id: " + id);
    }

    @Transactional
    @Override
    public void updateUser(Long id, String name, String email, String password) {
        User user = userDao.findById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userDao.update(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}