package jm.security.example.dao;

import jm.security.example.model.Role;
import jm.security.example.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);

    List<Role> allUsersRole();

    Role getRoleById(Long id);


}

