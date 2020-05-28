package com.mattos.fintech.bank.output.adapter;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.output.adapter.mongo.CreditCardAccountReactiveRepository;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreditCardAccountRepository implements CreditCardAccountStatePort, CreditCardQueryPort {

    private CreditCardAccountReactiveRepository creditCardAccountReactiveRepo;

    public CreditCardAccountRepository(CreditCardAccountReactiveRepository creditCardAccountReactiveRepo) {
        this.creditCardAccountReactiveRepo = creditCardAccountReactiveRepo;
    }

    @Override
    public Mono<CreditCardAccount> create(CreditCardAccount account) {
        return creditCardAccountReactiveRepo.insert(AccountEntity.fromCreditCardAccount(account))
                .map(savedAccount -> {
                    return (CreditCardAccount)AccountEntity.fromAccountEntity(savedAccount);
                });
    }

    @Override
    public Mono<CreditCardAccount> update(CreditCardAccount account) {
        return creditCardAccountReactiveRepo.save(AccountEntity.fromCreditCardAccount(account))
                .map(savedAccount -> {
                    return (CreditCardAccount)AccountEntity.fromAccountEntity(savedAccount);
                });
    }

    @Override
    public Flux<CreditCardAccount> listAllCreditCards(String accountHolderId) {
        return creditCardAccountReactiveRepo.findAll()
                .map(loadedEntity -> {return (CreditCardAccount)AccountEntity.fromAccountEntity(loadedEntity);});
    }
}
