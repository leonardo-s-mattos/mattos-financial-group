package com.mattos.fintech.management.output.adapter;

import com.mattos.fintech.management.domain.CreditCardTransaction;
import com.mattos.fintech.management.output.adapter.mongo.CreditCardTransactionRxRepo;
import com.mattos.fintech.management.output.port.CreditCardTransactionRepoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CreditCardTransactionMongoRepo implements CreditCardTransactionRepoPort {

    private CreditCardTransactionRxRepo mongoRxRepo;

    @Autowired
    public CreditCardTransactionMongoRepo(CreditCardTransactionRxRepo mongoRxRepo) {
        this.mongoRxRepo = mongoRxRepo;
    }

    @Override
    public Mono<CreditCardTransaction> save(CreditCardTransaction tx) {
        return null;
    }

    @Override
    public Mono<CreditCardTransaction> findById(UUID id) {
        return null;
    }
}
