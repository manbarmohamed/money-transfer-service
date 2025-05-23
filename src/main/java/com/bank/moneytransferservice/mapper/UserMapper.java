package com.bank.moneytransferservice.mapper;

import com.bank.moneytransferservice.dto.UserDto;
import com.bank.moneytransferservice.dto.UserRequest;
import com.bank.moneytransferservice.dto.UserResponse;
import com.bank.moneytransferservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest request);
    UserDto toDto(User user);
    UserResponse toResponse(User user);

    void updateEntityFromDto(UserDto dto,@MappingTarget User entity);
}
