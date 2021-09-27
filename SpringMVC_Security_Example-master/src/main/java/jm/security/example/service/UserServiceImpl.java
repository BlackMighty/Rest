package jm.security.example.service;

import jm.security.example.dao.UserDao;
import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void getUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public List<Role> allUsersRole() {
        return userDao.allUsersRole();
    }

    @Transactional
    public void saveUser(User user) {
        String password = user.getPassword();
        if (!((password.startsWith("$2a$") || password.startsWith("$2b$")) && password.length() == 60)) {
            user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        return userDao.getRoleById(id);
    }

    @Override
    public void setRole(User user, Long id) {

        if (id == 1) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        }
        if (id == 2) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        }
    }
}
