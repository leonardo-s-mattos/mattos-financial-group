package com.mattos.fintech.bank.domain.transaction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@SuperBuilder
public abstract class Transaction {

    @Id
    protected String transactionId;
    protected LocalDate transactionDate;
    protected BigDecimal amount;
    protected TransactionStatus status;

    public Transaction withId(){
        transactionId = UUID.randomUUID().toString();
        return this;
    }

}
