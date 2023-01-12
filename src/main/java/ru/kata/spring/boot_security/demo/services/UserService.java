package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void create(User user, List<String> listRoleId);

    void delete(long id);

    void update(User user, List<String> listRoleId);

    User getById(long id);
}
