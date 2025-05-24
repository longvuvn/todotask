package vn.hoidanit.todo.service;

import java.util.List;

import vn.hoidanit.todo.model.User;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(String id);

    User createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);

    User findByUsername(String username);

}
