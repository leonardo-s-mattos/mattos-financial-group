package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CreditCardQueryPort {

    Flux<CreditCardAccount> listAllCreditCards(String accountHolderId);
    Mono<CreditCardAccount> findById(String accountNumber);
}
