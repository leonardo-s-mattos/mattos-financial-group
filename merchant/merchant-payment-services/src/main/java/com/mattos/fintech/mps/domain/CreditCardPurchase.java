package com.mattos.fintech.mps.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@Component
public class CreditCardPurchase {

    @Id
    private UUID id;
    private String creditCardNumber;
    private BigDecimal amount;
    private PurchaseStatus purchaseStatus;
    private IssuerCompany issuerCompany;
    private LocalDateTime time;
    private String merchantId;

    public CreditCardPurchase withId(){
        id = UUID.randomUUID();
        return this;
    }

}
