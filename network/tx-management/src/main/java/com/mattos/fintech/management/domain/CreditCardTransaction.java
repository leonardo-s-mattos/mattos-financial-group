package com.mattos.fintech.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CreditCardTransaction {

    @Id
    private UUID id;
    @Indexed
    private String originationId;
    private String creditCardNumber;
    private BigDecimal originalAmount;
    private BigDecimal chargedFee;
    private TransactionStatus transactionStatus;
    private String acquirerBank;
    private String issuerBank;
    private LocalDateTime time;

    public CreditCardTransaction withId(){
        id = UUID.randomUUID();
        return this;
    }

    public CreditCardTransaction withStatus(TransactionStatus transactionStatus){
        this.transactionStatus = transactionStatus;
        return this;
    }

}
