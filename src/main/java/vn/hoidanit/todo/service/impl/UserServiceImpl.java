package vn.hoidanit.todo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.User;
import vn.hoidanit.todo.repository.UserRepository;
import vn.hoidanit.todo.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        return user;
    }

    @Override
    public User createUser(User user) {
        // Kiểm tra email có giá trị hợp lệ
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }

        // Kiểm tra username có giá trị hợp lệ
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username không được để trống");
        }

        // Kiểm tra password có giá trị hợp lệ
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password không được để trống");
        }

        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Kiểm tra username đã tồn tại chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại");
        }

        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        UUID uuid = UUID.fromString(id);
        User existingUser = this.userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        // Kiểm tra username mới có bị trùng không (nếu đã thay đổi)
        if (!existingUser.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại");
        }

        // Kiểm tra email mới có bị trùng không (nếu đã thay đổi)
        if (!existingUser.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Cập nhật thông tin
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        // Chỉ cập nhật password nếu có cung cấp
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }

        // Lưu đối tượng existingUser đã được cập nhật
        return this.userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        UUID uuid = UUID.fromString(id);
        User user = this.userRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        this.userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username không được để trống");
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));
    }

}
