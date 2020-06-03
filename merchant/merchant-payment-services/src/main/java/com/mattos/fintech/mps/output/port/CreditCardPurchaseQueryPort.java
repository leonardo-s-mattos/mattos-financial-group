package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import reactor.core.publisher.Flux;


public interface CreditCardPurchaseQueryPort {

    Flux<CreditCardPurchase> listAll();

    Flux<CreditCardPurchase> listApproved();
}
