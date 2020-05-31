package com.mattos.fintech.bank.output.adapter;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.output.adapter.mongo.CreditCardAccountReactiveRepository;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreditCardAccountRepository implements CreditCardAccountStatePort, CreditCardQueryPort {

    private CreditCardAccountReactiveRepository creditCardAccountReactiveRepo;

    @Autowired
    public CreditCardAccountRepository(CreditCardAccountReactiveRepository creditCardAccountReactiveRepo) {
        this.creditCardAccountReactiveRepo = creditCardAccountReactiveRepo;
    }

    @Override
    public Mono<CreditCardAccount> create(CreditCardAccount account) {
        return creditCardAccountReactiveRepo.save(account.withAccountNumber());
    }

    @Override
    public Mono<CreditCardAccount> update(CreditCardAccount account) {
        return creditCardAccountReactiveRepo.save(account);
    }

    @Override
    public Flux<CreditCardAccount> listAllCreditCards(String accountHolderId) {
        return creditCardAccountReactiveRepo.findAll();
    }

    @Override
    public Mono<CreditCardAccount> findById(String accountNumber) {
        return creditCardAccountReactiveRepo.findById(accountNumber);
    }
}
