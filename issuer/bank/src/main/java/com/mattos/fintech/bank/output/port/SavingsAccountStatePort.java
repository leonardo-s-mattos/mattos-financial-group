package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.SavingsAccount;
import reactor.core.publisher.Mono;


public interface SavingsAccountStatePort {

    Mono<SavingsAccount> create(SavingsAccount account);
    Mono<SavingsAccount> update(SavingsAccount account);

}
