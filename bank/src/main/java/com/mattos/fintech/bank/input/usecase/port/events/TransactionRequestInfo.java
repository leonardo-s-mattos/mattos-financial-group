package com.mattos.fintech.bank.input.usecase.port.events;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.domain.transaction.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TransactionRequestInfo {

    String transactionId;
    LocalDate transactionDate;
    String targetAccountNumber;
    BigDecimal amount;
    String comment;
    TransactionType transactionType;

    public TransactionRequestInfo withId(){
        transactionId = UUID.randomUUID().toString();
        return this;
    }

}
