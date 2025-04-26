package vn.hoidanit.todo.service;

import java.util.List;
import java.util.UUID;

import vn.hoidanit.todo.model.User;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(UUID id);

    User createUser(User user);

    User updateUser(UUID id, User user);

    void deleteUser(UUID id);
}
