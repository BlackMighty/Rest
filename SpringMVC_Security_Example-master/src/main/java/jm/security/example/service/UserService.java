package jm.security.example.service;

import jm.security.example.model.Role;
import jm.security.example.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    List<Role> allUsersRole();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserById(int id);

    Role getRoleById(int id);

    void setRole(User user, Long id);
}
