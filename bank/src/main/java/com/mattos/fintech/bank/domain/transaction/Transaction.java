package com.mattos.fintech.bank.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transaction {

    @Id
    protected String transactionId;
    protected LocalDate transactionDate;
    protected BigDecimal amount;
    protected TransactionStatus status;
    protected TransactionType transactionType;
    protected String targetAccount;
    protected String comment;



}
