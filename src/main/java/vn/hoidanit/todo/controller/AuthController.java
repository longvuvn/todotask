package vn.hoidanit.todo.controller;

import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.User;
import vn.hoidanit.todo.model.dto.LoginDTO;
import vn.hoidanit.todo.security.JwTokenProvider;
import vn.hoidanit.todo.service.impl.UserDetailCustom;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailCustom userDetailCustom;
    private final JwTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String token = tokenProvider.generateToken(authentication);

            // Lấy thông tin người dùng từ cơ sở dữ liệu
            User user = userDetailCustom.findByUsername(request.getUsername());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "user", Map.of(
                            "id", user.getId(),
                            "username", user.getUsername(),
                            "email", user.getEmail())));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Đăng nhập thất bại");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Kiểm tra xem username đã tồn tại chưa
            if (userDetailCustom.isUsernameExists(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            userDetailCustom.saveUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
