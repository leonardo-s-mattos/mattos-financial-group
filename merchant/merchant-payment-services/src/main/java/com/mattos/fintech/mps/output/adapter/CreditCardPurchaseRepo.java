package com.mattos.fintech.mps.output.adapter;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.output.adapter.mongo.CreditCardPurchaseRxRepo;
import com.mattos.fintech.mps.output.port.CreditCardPurchaseQueryPort;
import com.mattos.fintech.mps.output.port.CreditCardPurchaseStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreditCardPurchaseRepo implements CreditCardPurchaseStatePort, CreditCardPurchaseQueryPort {

    private CreditCardPurchaseRxRepo creditCardPurchaseRxRepo;

    @Autowired
    public CreditCardPurchaseRepo(CreditCardPurchaseRxRepo creditCardPurchaseRxRepo) {
        this.creditCardPurchaseRxRepo = creditCardPurchaseRxRepo;
    }

    @Override
    public Flux<CreditCardPurchase> listAll() {
        return creditCardPurchaseRxRepo.findAll();
    }

    @Override
    public Flux<CreditCardPurchase> listApproved() {
        return creditCardPurchaseRxRepo.findAll();
    }

    @Override
    public Mono<CreditCardPurchase> save(CreditCardPurchase purchase) {
        return creditCardPurchaseRxRepo.save(purchase);
    }
}
