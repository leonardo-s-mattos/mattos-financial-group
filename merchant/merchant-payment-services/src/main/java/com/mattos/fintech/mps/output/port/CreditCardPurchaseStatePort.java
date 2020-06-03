package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import reactor.core.publisher.Mono;


public interface CreditCardPurchaseStatePort {

    Mono<CreditCardPurchase> save(CreditCardPurchase account);

}
