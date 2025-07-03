package com.eaglebank.feature.account.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testBankAccountCreation() {
        // Given
        UUID accountId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String name = "Savings Account";
        AccountType accountType = AccountType.PERSONAL;
        String accountNumber = "12345678";
        String sortCode = "12-34-56";
        BigDecimal balance = BigDecimal.valueOf(1000.00);
        String currency = "GBP";
        ZonedDateTime createdTimestamp = ZonedDateTime.now();
        ZonedDateTime updatedTimestamp = ZonedDateTime.now();

        // When
        BankAccount bankAccount = BankAccount.builder()
                .accountId(accountId)
                .userId(userId)
                .name(name)
                .accountType(accountType)
                .accountNumber(accountNumber)
                .sortCode(sortCode)
                .balance(balance)
                .currency(currency)
                .createdTimestamp(createdTimestamp)
                .updatedTimestamp(updatedTimestamp)
                .build();

        // Then
        assertEquals(accountId, bankAccount.getAccountId());
        assertEquals(userId, bankAccount.getUserId());
        assertEquals(name, bankAccount.getName());
        assertEquals(accountType, bankAccount.getAccountType());
        assertEquals(accountNumber, bankAccount.getAccountNumber());
        assertEquals(sortCode, bankAccount.getSortCode());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currency, bankAccount.getCurrency());
        assertEquals(createdTimestamp, bankAccount.getCreatedTimestamp());
        assertEquals(updatedTimestamp, bankAccount.getUpdatedTimestamp());
    }

    @Test
    void testBankAccountEquality() {
        // Given
        UUID accountId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        
        BankAccount account1 = BankAccount.builder()
                .accountId(accountId)
                .userId(userId)
                .name("Savings Account")
                .accountType(AccountType.PERSONAL)
                .accountNumber("12345678")
                .sortCode("12-34-56")
                .balance(BigDecimal.valueOf(1000.00))
                .currency("GBP")
                .build();

        BankAccount account2 = BankAccount.builder()
                .accountId(accountId)
                .userId(userId)
                .name("Savings Account")
                .accountType(AccountType.PERSONAL)
                .accountNumber("12345678")
                .sortCode("12-34-56")
                .balance(BigDecimal.valueOf(1000.00))
                .currency("GBP")
                .build();

        // Then
        assertEquals(account1, account2);
        assertEquals(account1.hashCode(), account2.hashCode());
    }
}