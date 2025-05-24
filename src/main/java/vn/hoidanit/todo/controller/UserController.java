package vn.hoidanit.todo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.ApiResponse;
import vn.hoidanit.todo.model.User;
import vn.hoidanit.todo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // get all user
    @GetMapping()
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponse<List<User>> response = new ApiResponse<>(
                "success",
                "Users retrieved successfully",
                users,
                null,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        ApiResponse<User> response = new ApiResponse<>(
                "success",
                "User retrieved successfully",
                user,
                null,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        ApiResponse<User> response = new ApiResponse<>(
                "success",
                "User updated successfully",
                updatedUser,
                null,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, String>>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                "success",
                "User deleted successfully",
                Map.of("message", "User deleted successfully"),
                null,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
