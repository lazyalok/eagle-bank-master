package com.eaglebank.feature.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Identity domain entity representing a user's authentication credentials.
 * This is the core domain model for the Auth bounded context.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Identity {
    private UUID identityId;
    private String email;
    private String password;
    private UUID userId;
}