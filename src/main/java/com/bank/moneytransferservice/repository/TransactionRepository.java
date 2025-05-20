package com.bank.moneytransferservice.repository;

import com.bank.moneytransferservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySender_AccountNumber(String accountNumber);

    List<Transaction> findByReceiver_AccountNumber(String accountNumber);

    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findBySender_AccountNumberAndDateBetween(
            String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
}