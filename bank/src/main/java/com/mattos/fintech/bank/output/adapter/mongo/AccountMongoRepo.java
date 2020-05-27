package com.mattos.fintech.bank.output.adapter.mongo;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.output.port.AccountState;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMongoRepo extends ReactiveMongoRepository<Account, String> {

}
