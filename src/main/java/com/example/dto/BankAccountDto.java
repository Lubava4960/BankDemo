package com.example.dto;
/**
 * DTO банквского счёта
 */

import lombok.Data;

@Data
public class BankAccountDto {
    private Long id;
    private double balance;
    private String accountNumber;
}
