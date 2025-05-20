package com.bank.moneytransferservice.mapper;

import com.bank.moneytransferservice.dto.TransactionDto;
import com.bank.moneytransferservice.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "sender.accountNumber", target = "senderAccount")
    @Mapping(source = "receiver.accountNumber", target = "receiverAccount")
    TransactionDto toDto(Transaction transaction);
}
