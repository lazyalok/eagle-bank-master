package com.eaglebank.feature.user.service;

import com.eaglebank.feature.auth.domain.Identity;
import com.eaglebank.feature.auth.service.IdentityService;
import com.eaglebank.feature.user.repository.UserRepository;
import com.eaglebank.feature.user.domain.User;
import com.eaglebank.feature.user.web.model.CreateUserRequest;
import com.eaglebank.feature.user.web.model.UpdateUserRequest;
import com.eaglebank.feature.user.web.model.UserResponse;
import com.eaglebank.feature.common.exception.ResourceNotFoundException;
import com.eaglebank.feature.common.exception.ConflictException;
import com.eaglebank.feature.account.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final IdentityService identityService;
    private final BankAccountRepository bankAccountRepository;

    public UserService(UserRepository userRepository, IdentityService identityService, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.identityService = identityService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = user(createUserRequest);
        UUID userId = userRepository.createUser(user);
        identityService.createIdentity(userId, identity(createUserRequest));
        return userResponse(userId, user);
    }

    public UserResponse getUser(UUID userId) {
        User user = userRepository.getUser(userId);
        return userResponse(userId, user);
    }

    @Transactional
    public void updateUser(UUID userId, UpdateUserRequest updateUserRequest) {
        userRepository.updateUser(userId, user(updateUserRequest));
    }

    @Transactional
    public void deleteUser(UUID userId) {
        // Check if user exists
        User user = userRepository.getUser(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        // Check if user has any bank accounts
        int accountCount = bankAccountRepository.getAccountsByUserId(userId).size();
        if (accountCount > 0) {
            throw new ConflictException("User has bank account(s)");
        }
        userRepository.deleteUser(userId);
        identityService.deleteIdentity(userId);
    }

    private User user(CreateUserRequest createUserRequest) {
        return User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .phone(createUserRequest.getPhone())
                .build();
    }

    private User user(UpdateUserRequest updateUserRequest) {
        return User.builder()
                .name(updateUserRequest.getName())
                .phone(updateUserRequest.getPhone())
                .build();
    }

    private static Identity identity(CreateUserRequest createUserRequest) {
        return Identity.builder()
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .build();
    }

    private static UserResponse userResponse(UUID userId, User user) {
        return UserResponse.builder()
                .userId(userId)
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdTimestamp(user.getCreatedTimestamp())
                .updatedTimestamp(user.getUpdatedTimestamp())
                .build();
    }

}
