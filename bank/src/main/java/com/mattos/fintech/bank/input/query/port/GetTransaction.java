package com.mattos.fintech.bank.input.query.port;

import reactor.core.publisher.Flux;

public interface GetTransaction {

    Flux<TransactionInfo> getLastTransactions(String accountNumber);
}
