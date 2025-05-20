package com.bank.moneytransferservice.controller;


import com.bank.moneytransferservice.dto.AccountRequest;
import com.bank.moneytransferservice.dto.AccountResponse;
import com.bank.moneytransferservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
