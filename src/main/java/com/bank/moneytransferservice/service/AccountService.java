package com.bank.moneytransferservice.service;

import com.bank.moneytransferservice.dto.AccountRequest;
import com.bank.moneytransferservice.dto.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    AccountResponse updateAccount(Long id, AccountRequest request);
    void deleteAccount(Long id);
    AccountResponse getAccountById(Long id);
    List<AccountResponse> getAllAccounts();
}
