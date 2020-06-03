package com.mattos.fintech.mps.input.usecase.port;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.mps.domain.IssuerCompany;
import com.mattos.fintech.mps.domain.PurchaseStatus;
import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PayWithCard {

    Mono<String> requestAuthorization(Mono<PurchaseInfo> cardInfo);
    Mono<String> confirmPurchase(Mono<PurchaseInfo> purchaseInfo);

}
