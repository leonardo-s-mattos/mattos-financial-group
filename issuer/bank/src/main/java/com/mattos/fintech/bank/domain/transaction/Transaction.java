package com.mattos.fintech.bank.domain.transaction;

import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public abstract class Transaction {

    @Id
    protected String transactionId;
    protected LocalDate transactionDate;
    protected BigDecimal amount;
    protected TransactionStatus status;
    protected TransactionType transactionType;
    protected String targetAccount;
    protected String comment;

    public Transaction withId(){
        transactionId = UUID.randomUUID().toString();
        return this;
    }

}
