package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.Account;
import reactor.core.publisher.Mono;


public interface AccountState {

    Mono<Account> createAccount(Account account);
    Mono<Account> updateAccount(Account account);

}
