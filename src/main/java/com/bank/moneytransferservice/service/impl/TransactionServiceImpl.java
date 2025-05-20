package com.bank.moneytransferservice.service.impl;

import com.bank.moneytransferservice.dto.TransferRequestDto;
import com.bank.moneytransferservice.dto.TransferResponseDto;
import com.bank.moneytransferservice.entity.Account;
import com.bank.moneytransferservice.entity.Transaction;
import com.bank.moneytransferservice.exceptions.InsufficientBalanceException;
import com.bank.moneytransferservice.exceptions.ResourceNotFoundException;
import com.bank.moneytransferservice.mapper.TransactionMapper;
import com.bank.moneytransferservice.repository.AccountRepository;
import com.bank.moneytransferservice.repository.TransactionRepository;
import com.bank.moneytransferservice.repository.UserRepository;
import com.bank.moneytransferservice.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransferResponseDto executeTransfer(TransferRequestDto transferRequestDto) {
        validateTransferRequest(transferRequestDto);

        // Fetch accounts with pessimistic lock to prevent concurrent modifications
        Account senderAccount = accountRepository.findByAccountNumberWithLock(transferRequestDto.getFromAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found: " + transferRequestDto.getFromAccount()));

        Account receiverAccount = accountRepository.findByAccountNumberWithLock(transferRequestDto.getToAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found: " + transferRequestDto.getToAccount()));

        // Check if sender has sufficient balance
        if (senderAccount.getBalance() < transferRequestDto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance in sender account");
        }

        try {
            // Update account balances
            senderAccount.setBalance(senderAccount.getBalance() - transferRequestDto.getAmount());
            receiverAccount.setBalance(receiverAccount.getBalance() + transferRequestDto.getAmount());

            // Save updated accounts
            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);

            // Create and save transaction record
            Transaction transaction = transactionMapper.transferRequestToTransaction(transferRequestDto);
            transaction.setSender(senderAccount);
            transaction.setReceiver(receiverAccount);
            Transaction savedTransaction = transactionRepository.save(transaction);

            log.info("Transfer completed successfully: {} {} from account {} to account {}",
                    //transferRequestDto.getAmount(), senderAccount.getCurrency(),
                    transferRequestDto.getFromAccount(), transferRequestDto.getToAccount());

            // Create response with updated balances
            TransferResponseDto response = new TransferResponseDto();
            response.setMessage("Transfer completed successfully");
            response.setNewSenderBalance(senderAccount.getBalance());
            response.setNewReceiverBalance(receiverAccount.getBalance());

            return response;

        } catch (Exception e) {
            log.error("Error during transfer: {}", e.getMessage(), e);
            throw e; // Will trigger transaction rollback
        }
    }

    private void validateTransferRequest(TransferRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Transfer request cannot be null");
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (request.getFromAccount() == null || request.getFromAccount().trim().isEmpty()) {
            throw new IllegalArgumentException("Sender account cannot be empty");
        }

        if (request.getToAccount() == null || request.getToAccount().trim().isEmpty()) {
            throw new IllegalArgumentException("Receiver account cannot be empty");
        }

        if (Objects.equals(request.getFromAccount(), request.getToAccount())) {
            throw new IllegalArgumentException("Sender and receiver accounts cannot be the same");
        }
    }
}
