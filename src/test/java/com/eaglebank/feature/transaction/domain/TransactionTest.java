package com.eaglebank.feature.transaction.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionCreation() {
        // Given
        UUID transactionId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        BigDecimal amount = BigDecimal.valueOf(100.00);
        TransactionType type = TransactionType.DEPOSIT;
        ZonedDateTime timestamp = ZonedDateTime.now();

        // When
        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .accountId(accountId)
                .amount(amount)
                .type(type)
                .timestamp(timestamp)
                .build();

        // Then
        assertEquals(transactionId, transaction.getTransactionId());
        assertEquals(accountId, transaction.getAccountId());
        assertEquals(amount, transaction.getAmount());
        assertEquals(type, transaction.getType());
        assertEquals(timestamp, transaction.getTimestamp());
    }

    @Test
    void testTransactionEquality() {
        // Given
        UUID transactionId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        
        Transaction transaction1 = Transaction.builder()
                .transactionId(transactionId)
                .accountId(accountId)
                .amount(BigDecimal.valueOf(100.00))
                .type(TransactionType.DEPOSIT)
                .timestamp(ZonedDateTime.now())
                .build();

        Transaction transaction2 = Transaction.builder()
                .transactionId(transactionId)
                .accountId(accountId)
                .amount(BigDecimal.valueOf(100.00))
                .type(TransactionType.DEPOSIT)
                .timestamp(transaction1.getTimestamp())
                .build();

        // Then
        assertEquals(transaction1, transaction2);
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }
}