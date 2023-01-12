package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            Hibernate.initialize(u.getRoleSet());
        }
        return users;
    }

    @Transactional
    @Override
    public void create(User user, List<String> listRoleId) {
        User userForSave = prepareUserForSave(user, listRoleId);
        userRepository.save(Objects.requireNonNull(userForSave, "User can’t be null"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(User user, List<String> listRoleId) {
        User userForSave = prepareUserForSave(user, listRoleId);
        userRepository.save(Objects.requireNonNull(userForSave, "User can’t be null"));
    }

    @Transactional
    @Override
    public User getById(long id) {
        User user = userRepository.findById(id).orElse(null);
        Hibernate.initialize(user.getRoleSet());
        return user;
    }

    private User prepareUserForSave(User user, List<String> listRoleId) {
        Set<Role> userRole = new HashSet<>();
        for (String roleId : listRoleId) {
            Role role = roleService.getById(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoleSet(userRole);
        return user;
    }
}
