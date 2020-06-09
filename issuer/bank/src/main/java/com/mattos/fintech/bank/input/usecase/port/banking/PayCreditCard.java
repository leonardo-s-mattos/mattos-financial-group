package com.mattos.fintech.bank.input.usecase.port.banking;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import reactor.core.publisher.Mono;

public interface PayCreditCard {

    Mono<String> pay(Mono<TransactionRequestInfo> transactionRequest);
    Mono<String> revert(Mono<TransactionRequestInfo> transactionRequest);
    Mono<String> post(Mono<TransactionRequestInfo> transactionRequest);



}
