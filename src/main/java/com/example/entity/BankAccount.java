package com.example.entity;
/**
 * банковский аккаунт
 */

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "initial_deposit", nullable = false)
    private BigDecimal initialDeposit;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;




}
