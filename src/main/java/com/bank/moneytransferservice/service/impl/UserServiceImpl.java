package com.bank.moneytransferservice.service.impl;

import com.bank.moneytransferservice.dto.UserRequest;
import com.bank.moneytransferservice.dto.UserResponse;
import com.bank.moneytransferservice.exceptions.ResourceNotFoundException;
import com.bank.moneytransferservice.mapper.UserMapper;
import com.bank.moneytransferservice.repository.UserRepository;
import com.bank.moneytransferservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bank.moneytransferservice.entity.User;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users.stream()
                    .map(userMapper::toResponse)
                    .toList();
        }
        return List.of();
    }
}
