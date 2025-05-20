package com.bank.moneytransferservice.dto;


import lombok.Data;

@Data

public class AccountDto {
    private Long id;
    private String accountNumber;
    private Double balance;
    private Long userId;
}
