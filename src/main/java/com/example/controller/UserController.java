package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Operation(
            summary = "Возможно добавить пользователя",
            tags = "Пользователь"
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam BigDecimal initialDeposit) {
        User createdUser = userService.createUser(user, initialDeposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @Operation(
            summary = "Возможно обновить данные пользователя",
            tags = "Пользователь"
    )
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        updatedUser.setId(userId);
        User updatedUserEntity = userService.updateUser(updatedUser);
        return ResponseEntity.ok(updatedUserEntity);
    }
    @Operation(
            summary = "Возможно удалить данные пользователя",
            tags="Пользователь"
    )

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }




}
