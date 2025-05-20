package com.bank.moneytransferservice.mapper;

import com.bank.moneytransferservice.dto.AccountDto;
import com.bank.moneytransferservice.dto.AccountRequest;
import com.bank.moneytransferservice.dto.AccountResponse;
import com.bank.moneytransferservice.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequest request);
    AccountDto toDto(Account account);
    AccountResponse toResponse(Account account);
}
