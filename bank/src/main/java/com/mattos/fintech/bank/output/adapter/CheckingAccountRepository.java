package com.mattos.fintech.bank.output.adapter;

import com.mattos.fintech.bank.domain.account.CheckingAccount;
import com.mattos.fintech.bank.output.adapter.mongo.CheckingAccountReactiveRepository;
import com.mattos.fintech.bank.output.port.CheckingAccountQueryPort;
import com.mattos.fintech.bank.output.port.CheckingAccountStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CheckingAccountRepository implements CheckingAccountStatePort, CheckingAccountQueryPort {

    private CheckingAccountReactiveRepository checkingAccountReactiveRepo;

    @Autowired
    public CheckingAccountRepository(CheckingAccountReactiveRepository checkingAccountReactiveRepo) {
        this.checkingAccountReactiveRepo = checkingAccountReactiveRepo;
    }

    @Override
    public Mono<CheckingAccount> create(CheckingAccount account) {
        return checkingAccountReactiveRepo.insert(account.withAccountNumber());
    }

    @Override
    public Mono<CheckingAccount> update(CheckingAccount account) {
        return checkingAccountReactiveRepo.save(account);
    }

    @Override
    public Flux<CheckingAccount> listAllAccounts(String accountHolderId) {
        return checkingAccountReactiveRepo.findAll();
    }
}
