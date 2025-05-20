package com.bank.moneytransferservice.mapper;

import com.bank.moneytransferservice.dto.TransactionDto;
import com.bank.moneytransferservice.dto.TransferRequestDto;
import com.bank.moneytransferservice.entity.Account;
import com.bank.moneytransferservice.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "fromAccount", target = "sender", qualifiedByName = "accountIdToAccount")
    @Mapping(source = "toAccount", target = "receiver", qualifiedByName = "accountIdToAccount")
    @Mapping(target = "type", constant = "TRANSFER")
    @Mapping(target = "date", expression = "java(getCurrentDateTime())")
    Transaction transferRequestToTransaction(TransferRequestDto transferRequestDto);

    @Mapping(source = "sender.accountNumber", target = "senderAccount")
    @Mapping(source = "receiver.accountNumber", target = "receiverAccount")
    TransactionDto transactionToTransactionDto(Transaction transaction);

    @Named("accountIdToAccount")
    default Account accountIdToAccount(String accountId) {
        if (accountId == null) {
            return null;
        }

        Account account = new Account();
        account.setAccountNumber(accountId);
        return account;

        // Note: In a real application, you would typically inject an AccountRepository
        // and look up the complete account entity. This is a simplified implementation.
    }

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}