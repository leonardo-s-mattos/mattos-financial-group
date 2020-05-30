package com.mattos.fintech.bank.output.adapter.mongo;

import com.mattos.fintech.bank.domain.account.CheckingAccount;
import com.mattos.fintech.bank.domain.account.SavingsAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountReactiveRepository extends ReactiveMongoRepository<SavingsAccount, String> {

}
