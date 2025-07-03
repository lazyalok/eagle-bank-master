package com.eaglebank.feature.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * User domain entity representing a bank customer.
 * This is the core domain model for the User bounded context.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID userId;
    private String name;
    private String email;
    private String phone;
    private ZonedDateTime createdTimestamp;
    private ZonedDateTime updatedTimestamp;
}