package com.mattos.fintech.bank.output.adapter;

import com.mattos.fintech.bank.domain.account.SavingsAccount;
import com.mattos.fintech.bank.output.adapter.mongo.SavingsAccountReactiveRepository;
import com.mattos.fintech.bank.output.port.SavingsAccountQueryPort;
import com.mattos.fintech.bank.output.port.SavingsAccountStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SavingsAccountRepository implements SavingsAccountStatePort, SavingsAccountQueryPort {

    private SavingsAccountReactiveRepository savingsAccountReactiveRepo;

    @Autowired
    public SavingsAccountRepository(SavingsAccountReactiveRepository savingsAccountReactiveRepo) {
        this.savingsAccountReactiveRepo = savingsAccountReactiveRepo;
    }

    @Override
    public Mono<SavingsAccount> create(SavingsAccount account) {
        return savingsAccountReactiveRepo.insert(account.withAccountNumber());
    }

    @Override
    public Mono<SavingsAccount> update(SavingsAccount account) {
        return savingsAccountReactiveRepo.save(account);
    }

    @Override
    public Flux<SavingsAccount> listAllAccounts(String accountHolderId) {
        return savingsAccountReactiveRepo.findAll();
    }
}
