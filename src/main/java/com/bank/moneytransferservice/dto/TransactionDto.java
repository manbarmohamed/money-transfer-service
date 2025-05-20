package com.bank.moneytransferservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private String senderAccount;
    private String receiverAccount;
    private Double amount;
    private LocalDateTime date;
    private String type;
}
