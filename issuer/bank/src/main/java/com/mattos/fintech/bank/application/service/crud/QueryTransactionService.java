package com.mattos.fintech.bank.application.service.crud;

import com.mattos.fintech.bank.input.query.port.*;
import com.mattos.fintech.bank.output.port.CheckingAccountQueryPort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.SavingsAccountQueryPort;
import com.mattos.fintech.bank.output.port.TransactionQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class QueryTransactionService implements GetTransaction {

    private final TransactionQueryPort transactionQueryPort;


    @Autowired
    public QueryTransactionService(TransactionQueryPort transactionQueryPort) {
        this.transactionQueryPort = transactionQueryPort;

    }

    @Override
    public Flux<TransactionInfo> getLastTransactions(String accountNumber) {
        return transactionQueryPort.listAllTransactions(accountNumber)
                .map( transaction -> TransactionInfo.builder()
                        .amount(transaction.getAmount())
                        .comment(transaction.getComment())
                        .transactionId(transaction.getTransactionId())
                        .transactionDate(transaction.getTransactionDate()).build());
    }
}
