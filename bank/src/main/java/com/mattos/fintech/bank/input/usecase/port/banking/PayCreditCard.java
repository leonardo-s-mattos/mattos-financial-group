package com.mattos.fintech.bank.input.usecase.port.banking;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import reactor.core.publisher.Flux;

public interface PayCreditCard {

    Flux<String> pay(Flux<Transaction> transactionRequest);
    Flux<String> revert(Flux<Transaction> transactionRequest);
    Flux<String> post(Flux<Transaction> transactionRequest);


}
