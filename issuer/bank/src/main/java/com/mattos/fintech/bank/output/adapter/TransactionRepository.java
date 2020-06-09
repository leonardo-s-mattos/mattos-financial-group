package com.mattos.fintech.bank.output.adapter;

import com.mattos.fintech.bank.domain.account.SavingsAccount;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.output.adapter.mongo.SavingsAccountReactiveRepository;
import com.mattos.fintech.bank.output.adapter.mongo.TransactionReactiveRepository;
import com.mattos.fintech.bank.output.port.SavingsAccountQueryPort;
import com.mattos.fintech.bank.output.port.SavingsAccountStatePort;
import com.mattos.fintech.bank.output.port.TransactionLogPort;
import com.mattos.fintech.bank.output.port.TransactionQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TransactionRepository implements TransactionLogPort, TransactionQueryPort {

    private TransactionReactiveRepository transactionReactiveRepository;

    @Autowired
    public TransactionRepository(TransactionReactiveRepository transactionReactiveRepository) {
        this.transactionReactiveRepository = transactionReactiveRepository;
    }

    @Override
    public Mono<Transaction> log(Transaction tx) {
        return transactionReactiveRepository.insert(tx);
    }


    @Override
    public Flux<Transaction> listAllTransactions(String accountNumber) {
        return transactionReactiveRepository.findAll();
    }
}
