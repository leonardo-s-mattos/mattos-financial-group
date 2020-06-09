package com.mattos.fintech.bank.input.query.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.bank.domain.transaction.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInfo {

    String transactionId;
    LocalDate transactionDate;
    String targetAccountNumber;
    BigDecimal amount;
    String comment;
    TransactionType transactionType;

}
