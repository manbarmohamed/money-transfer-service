package com.bank.moneytransferservice.dto;


import lombok.Data;

@Data

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private Double balance;
    private String userFullName;
}
