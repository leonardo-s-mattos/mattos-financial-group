package com.mattos.fintech.bank.input.query.port;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetAccount {

    Flux<CreditCardInfo> listAllOpenCards(String accountHolderId);
    Flux<BankingAccountInfo> listAllOpenCheckingAccounts(String accountHolderId);
    Flux<BankingAccountInfo> listAllOpenSavingsAccounts(String accountHolderId);
    Mono<CreditCardInfo> findCardById(String creditCardNumber);

}
