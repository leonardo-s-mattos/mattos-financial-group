package com.mattos.fintech.bank.domain.transaction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@SuperBuilder
public abstract class Transaction {

    protected UUID transactionId;
    protected LocalDate transactionDate;
    protected BigDecimal amount;
    protected TransactionStatus status;

}
