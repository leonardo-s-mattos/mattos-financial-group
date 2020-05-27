package com.mattos.fintech.bank.output.adapter.mongo;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.output.port.AccountQuery;
import com.mattos.fintech.bank.output.port.AccountState;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountRepo implements AccountState, AccountQuery {

    private AccountMongoRepo mongoRepo;

    public AccountRepo(AccountMongoRepo mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return mongoRepo.save(account);
    }

    @Override
    public Mono<Account> updateAccount(Account account) {
        return mongoRepo.save(account);
    }

    @Override
    public Flux<Account> listAllCreditCards(String accountHolderId) {
        return mongoRepo.findAll();
    }
}
