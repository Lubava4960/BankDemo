package com.example.controller;

import com.example.dto.CreateOrUpdateUserDto;
import com.example.dto.Login;
import com.example.dto.RegisterDTO;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Авторизация пользователей",
            tags =  "Авторизация"
    )
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Login login) {
        boolean userDto = authService.login(login.getUsername(), login.getPassword());
        return ResponseEntity.ok(userDto);
    }

    @Operation(
            summary = "Регистрация пользователе",
            tags =  "Регистрация"
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateOrUpdateUserDto createUserDto) {
        if (authService.register(new RegisterDTO())) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
