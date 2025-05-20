package com.bank.moneytransferservice.dto;


import lombok.Data;

@Data

public class AccountRequest {
    private String accountNumber;
    private Double balance;
    private Long userId;
}
