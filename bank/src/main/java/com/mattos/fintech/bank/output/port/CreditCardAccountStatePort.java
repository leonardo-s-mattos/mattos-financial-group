package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import reactor.core.publisher.Mono;


public interface CreditCardAccountStatePort {

    Mono<CreditCardAccount> create(CreditCardAccount account);
    Mono<CreditCardAccount> update(CreditCardAccount account);

}
