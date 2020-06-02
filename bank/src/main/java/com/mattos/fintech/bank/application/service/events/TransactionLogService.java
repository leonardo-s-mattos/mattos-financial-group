package com.mattos.fintech.bank.application.service.events;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.domain.transaction.TransactionStatus;
import com.mattos.fintech.bank.input.usecase.port.events.LogTransaction;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.TransactionLogPort;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import reactor.core.publisher.Flux;

@EnableBinding(TransactionEvents.NewTransactionEvent.class)
public class TransactionLogService implements LogTransaction {

    private TransactionLogPort logPort;

    public TransactionLogService(TransactionLogPort logPort) {
        this.logPort = logPort;
    }

    @Override
    @StreamListener(TransactionEvents.NewTransactionEvent.NEW_TRANSACTION)
    @Output(TransactionEvents.NewTransactionEvent.LOGGED_TRANSACTION_NOTIFICATION)
    public Flux<Transaction> logTransaction(Flux<TransactionRequestInfo> request) {
        return request
                .map(requestInfo -> requestInfo.getTransactionType().builder()
                    .transactionDate(requestInfo.getTransactionDate())
                    .transactionId(requestInfo.getTransactionId())
                    .amount(requestInfo.getAmount())
                    .targetAccount(requestInfo.getTargetAccountNumber())
                    .status(TransactionStatus.PENDING)
                    .transactionType(requestInfo.getTransactionType()).build())

                .flatMap(tx -> logPort.log(tx));

    }
}
