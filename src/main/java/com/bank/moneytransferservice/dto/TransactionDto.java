package com.bank.moneytransferservice.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private String senderAccount;
    private String receiverAccount;
    private Double amount;
    private LocalDateTime date;
    private String type;
}
