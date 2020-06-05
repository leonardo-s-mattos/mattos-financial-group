package com.mattos.fintech.management.output.port;

import com.mattos.fintech.management.domain.CreditCardTransaction;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CreditCardTransactionRepoPort {

    Mono<CreditCardTransaction> save(CreditCardTransaction tx);
    Mono<CreditCardTransaction> findById(UUID id);
    Mono<CreditCardTransaction> findByOriginationId(String originationId);
}
