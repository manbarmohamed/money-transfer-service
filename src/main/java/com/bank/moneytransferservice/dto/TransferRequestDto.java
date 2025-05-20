package com.bank.moneytransferservice.dto;


import lombok.Data;

@Data
public class TransferRequestDto {
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
