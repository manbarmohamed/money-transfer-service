package com.bank.moneytransferservice.service;

import com.bank.moneytransferservice.dto.UserRequest;
import com.bank.moneytransferservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
}
