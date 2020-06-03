package com.mattos.fintech.mps.input.usecase.port;

import reactor.core.publisher.Mono;

public interface PayWithCard {

    Mono<String> requestAuthorization(Mono<PurchaseInfo> cardInfo);
    Mono<String> confirmPurchase(Mono<PurchaseInfo> purchaseInfo);

}
