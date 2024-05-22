package com.example.repository;

import com.example.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository {
    BankAccount findByUserId(Long UserId);

}
