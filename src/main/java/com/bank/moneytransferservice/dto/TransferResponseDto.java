package com.bank.moneytransferservice.dto;


import lombok.Data;

@Data
public class TransferResponseDto {
    private String message;
    private Double newSenderBalance;
    private Double newReceiverBalance;
}
