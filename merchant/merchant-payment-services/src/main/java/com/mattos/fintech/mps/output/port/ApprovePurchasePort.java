package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import reactor.core.publisher.Mono;

public interface ApprovePurchasePort {

    void requestPurchaseAuthorization(CreditCardPurchase purchase);
    Mono<String> informConfirmedPurchase(CreditCardPurchase purchase);
}
