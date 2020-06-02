package com.mattos.fintech.bank.input.usecase.port.events;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import reactor.core.publisher.Flux;

public interface LogTransaction {


    Flux<Transaction> logTransaction(Flux<TransactionRequestInfo> request);

}
