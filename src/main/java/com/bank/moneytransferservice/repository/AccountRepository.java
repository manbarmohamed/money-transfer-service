package com.bank.moneytransferservice.repository;

import com.bank.moneytransferservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByOwnerId(Long userId);

    boolean existsByAccountNumber(String accountNumber);
}