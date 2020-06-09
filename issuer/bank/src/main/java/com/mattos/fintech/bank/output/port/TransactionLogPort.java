package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.SavingsAccount;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import reactor.core.publisher.Mono;


public interface TransactionLogPort {

    Mono<Transaction> log(Transaction tx);

}
