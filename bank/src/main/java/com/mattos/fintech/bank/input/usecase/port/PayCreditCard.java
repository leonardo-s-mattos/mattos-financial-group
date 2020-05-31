package com.mattos.fintech.bank.input.usecase.port;

import reactor.core.publisher.Mono;

public interface PayCreditCard {

    Mono<String> pay(Mono<TransactionRequestInfo> transactionRequestInfo);
    Mono<String> revert(Mono<TransactionRequestInfo> transactionRequestInfo);
    Mono<String> post(Mono<TransactionRequestInfo> transactionRequestInfo);

}
