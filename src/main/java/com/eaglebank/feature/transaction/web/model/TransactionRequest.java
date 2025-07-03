package com.eaglebank.feature.transaction.web.model;

import com.eaglebank.feature.transaction.domain.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema
public class TransactionRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType type;
    private String currency;
}
