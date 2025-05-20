package com.bank.moneytransferservice.service.impl;

import com.bank.moneytransferservice.dto.AccountRequest;
import com.bank.moneytransferservice.dto.AccountResponse;
import com.bank.moneytransferservice.entity.Account;
import com.bank.moneytransferservice.entity.User;
import com.bank.moneytransferservice.mapper.AccountMapper;
import com.bank.moneytransferservice.repository.AccountRepository;
import com.bank.moneytransferservice.repository.UserRepository;
import com.bank.moneytransferservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;

    @Override
    public AccountResponse createAccount(AccountRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (accountRepository.existsByOwnerId(user.getId())) {
            throw new RuntimeException("User already has an account");
        }
        String accountNumber = generateUniqueAccountNumber();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0);
        account.setOwner(user);
        account = accountRepository.save(account);
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse updateAccount(Long id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (request.getBalance() != null) {
            account.setBalance(request.getBalance());
        }
        account = accountRepository.save(account);
        return accountMapper.toResponse(account);

    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public AccountResponse getAccountById(Long id) {
        return null;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            return accounts.stream()
                    .map(accountMapper::toResponse)
                    .toList();
        }
        return List.of();
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = "AC" + String.format("%010d", new Random().nextInt(1_000_000_000));
        } while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}
