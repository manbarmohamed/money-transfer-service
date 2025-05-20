package com.bank.moneytransferservice.service;

import com.bank.moneytransferservice.dto.TransferRequestDto;
import com.bank.moneytransferservice.dto.TransferResponseDto;

public interface TransactionService {
    TransferResponseDto executeTransfer(TransferRequestDto requestDto);
}