package com.mattos.fintech.bank.input.usecase.port;

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
public class TransactionRequestInfo {

    String transactionId;
    LocalDate transactionDate;
    String targetAccountNumber;
    BigDecimal amount;
    String comment;

}
