package com.bank.moneytransferservice.controller;


import com.bank.moneytransferservice.dto.AccountRequest;
import com.bank.moneytransferservice.dto.AccountResponse;
import com.bank.moneytransferservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        AccountResponse accountResponse = accountService.createAccount(request);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accountResponses = accountService.getAllAccounts();
        return ResponseEntity.ok(accountResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @RequestBody AccountRequest request) {
        AccountResponse accountResponse = accountService.updateAccount(id, request);
        return ResponseEntity.ok(accountResponse);
    }
}
