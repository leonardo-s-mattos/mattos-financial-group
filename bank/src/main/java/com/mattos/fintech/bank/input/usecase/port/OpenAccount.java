package com.mattos.fintech.bank.input.usecase.port;

import reactor.core.publisher.Mono;

public interface OpenAccount {

    Mono<String> openCreditCardAccount(CreditCardRequestInfo request);
    Mono<String> openCheckingAccount(BankingAccountRequestInfo request);
    Mono<String> openSavingsAccount(BankingAccountRequestInfo request);
}
