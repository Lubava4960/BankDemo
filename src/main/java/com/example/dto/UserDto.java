package com.example.dto;
/**
 * DTO пользователя
 */

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private Set<String> phones;
    private Set<String> emails;
    private BankAccountDto bankAccount;


}
