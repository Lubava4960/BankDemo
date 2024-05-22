package com.example.dto;
/**
 * DTO регистрации
 */
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class RegisterDTO {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;


}
