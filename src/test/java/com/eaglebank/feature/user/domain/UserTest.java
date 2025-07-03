package com.eaglebank.feature.user.domain;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        // Given
        UUID userId = UUID.randomUUID();
        String name = "John Doe";
        String email = "john.doe@example.com";
        String phone = "1234567890";
        ZonedDateTime createdTimestamp = ZonedDateTime.now();
        ZonedDateTime updatedTimestamp = ZonedDateTime.now();

        // When
        User user = User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .phone(phone)
                .createdTimestamp(createdTimestamp)
                .updatedTimestamp(updatedTimestamp)
                .build();

        // Then
        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(createdTimestamp, user.getCreatedTimestamp());
        assertEquals(updatedTimestamp, user.getUpdatedTimestamp());
    }

    @Test
    void testUserEquality() {
        // Given
        UUID userId = UUID.randomUUID();
        User user1 = User.builder()
                .userId(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .build();

        User user2 = User.builder()
                .userId(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .build();

        // Then
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}