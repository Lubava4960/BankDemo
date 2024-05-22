package com.example.service;

import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Data
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
@Autowired
public AuthService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
}

    public boolean login(String userName, String password) {
        if (!userRepository.existsByUsername(userName)) {
            return false;
        }
        User user = userRepository.findByUserName(userName).orElseTrow(()->new UsernameNotFoundException("Юзер не найден"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean register(RegisterDTO register) {
        if (userRepository.existsByUsername(register.getUserName())) {
            return false;
        }
        userRepository.save(
                User.builder()
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .password(passwordEncoder.encode(register.getPassword()))
                        .userName(register.getUserName())
                        .dateOfBirth(register.getDateOfBirth())
                        .phone(register.getPhone())
                        .build());
        return true;
    }
}
