package com.example.dto;
/**
 * DTO щбновления и создания Польщователя
 */

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class CreateOrUpdateUserDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private Set<String> phones;
    private Set<String> emails;


}
