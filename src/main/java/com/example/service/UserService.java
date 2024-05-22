package com.example.service;
/**
 * Сервис пользователь
 */


import com.example.entity.BankAccount;
import com.example.entity.User;
import com.example.repository.AccountRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * создание пользователя
     * @param user
     * @param initialDeposit
     * @return
     */
    public User createUser(User user, BigDecimal initialDeposit) {
        /**
         * Проверка, что логин, телефон и email не заняты
         */
        if (userRepository.findByUsername(user.getUserName()) != null) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new IllegalArgumentException("Phone number is already taken");
        }
        if (userRepository.findByEmail(user.getEmails().toString()) != null) {
            throw new IllegalArgumentException("Email is already taken");
        }
        /**
         *  Шифрование пароля
         */
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        /**
         * Сохранение пользователя
         */
        User savedUser = userRepository.save(user);

        /**
         * Создание банковского счета с начальным депозитом
         */
        BankAccount account = new BankAccount();
        account.setUser(savedUser);
        account.setInitialDeposit(initialDeposit);
        account.setBalance(initialDeposit);
        BankAccount savedAccount = (BankAccount) accountRepository.save(account);

        savedUser.setBankAccount(savedAccount);
        return userRepository.save(savedUser);

    }

    /**
     * Метод обновления
     * @param updatedUser
     * @return
     */
    @Transactional
    public User updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        /**
         * Проверка уникальности нового телефона и email (если они были изменены)
         */

        if (!Objects.equals(existingUser.getPhone(), updatedUser.getPhone())) {
            if (userRepository.findByPhone(updatedUser.getPhone()) != null) {
                throw new IllegalArgumentException("Phone number is already taken");
            }
            existingUser.setPhone(updatedUser.getPhone());
        }

        if (!Objects.equals(existingUser.getEmails(), updatedUser.getEmails())) {
            if (userRepository.findByEmail(updatedUser.getEmails().toString()) != null) {
                throw new IllegalArgumentException("Email is already taken");
            }
            existingUser.setEmails(updatedUser.getEmails());
        }

        /**
         *  Сохранение обновленного пользователя
         */
        return userRepository.save(existingUser);
    }

    /**
     * Удаление данных пользователя
     * @param userId
     */
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        /**
         * Проверка, что пользователь не пытается удалить последний контактный адрес
         */
        if (user.getPhone() == null && user.getEmails() == null) {
            throw new IllegalArgumentException("Cannot remove the last contact address");
        }

        if (user.getPhone() != null) {
            user.setPhone(null);
        } else if (user.getEmails() != null) {
            user.setEmails(null);
        }

        userRepository.save(user);
    }



}
