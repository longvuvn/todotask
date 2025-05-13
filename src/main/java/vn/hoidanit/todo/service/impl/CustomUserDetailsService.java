package vn.hoidanit.todo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import vn.hoidanit.todo.model.User;
import vn.hoidanit.todo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
    }

    public boolean isUsernameExists(String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!StringUtils.hasText(user.getUsername())) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (isUsernameExists(user.getUsername())) {
            throw new IllegalStateException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
    }
}