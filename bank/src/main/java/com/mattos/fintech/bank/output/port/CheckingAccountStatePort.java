package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.CheckingAccount;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import reactor.core.publisher.Mono;


public interface CheckingAccountStatePort {

    Mono<CheckingAccount> create(CheckingAccount account);
    Mono<CheckingAccount> update(CheckingAccount account);

}
