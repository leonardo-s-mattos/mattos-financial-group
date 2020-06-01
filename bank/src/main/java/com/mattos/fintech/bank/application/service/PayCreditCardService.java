package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.application.service.events.TransactionEvents.*;
import com.mattos.fintech.bank.application.service.exception.InvalidTransactionRequestException;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.TransactionStatus;
import com.mattos.fintech.bank.input.usecase.port.PayCreditCard;
import com.mattos.fintech.bank.input.usecase.port.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@Component
@EnableBinding(NewTransactionEvent.class)
public class PayCreditCardService implements PayCreditCard {

    private CreditCardQueryPort queryPort;
    private CreditCardAccountStatePort statePort;

    private NewTransactionEvent processor;

    @Autowired
    public PayCreditCardService(CreditCardQueryPort queryPort, CreditCardAccountStatePort statePort, NewTransactionEvent processor) {
        this.queryPort = queryPort;
        this.statePort = statePort;
        this.processor = processor;
    }

    @Override
    public Mono<String> pay(Mono<TransactionRequestInfo> transactionRequestInfo) {


        return Mono.zip(transactionRequestInfo.map(requestInfo ->
                        (CreditCardPayment)CreditCardPayment.builder().transactionDate(requestInfo.getTransactionDate())
                            .amount(requestInfo.getAmount())
                            .status(TransactionStatus.PENDING).build()),
                transactionRequestInfo.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccountNumber()).block()))

                .map(TupleUtils.function ((tx, account) ->  {
                    if (account.pay(tx)) {
                        statePort.update(account).subscribe();
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment of number " + account.getLastFourDigits());
                    }

                    processor.output().send(message(tx.withId()));

                    return tx.getTransactionId();
                }))
                ;

    }

    @Override
    public Mono<String> revert(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return Mono.zip(transactionRequestInfo.map(requestInfo ->
                        (CreditCardPayment)CreditCardPayment.builder().transactionDate(requestInfo.getTransactionDate())
                                .amount(requestInfo.getAmount())
                                .status(TransactionStatus.PENDING).build()),
                transactionRequestInfo.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccountNumber()).block()))

                .map(TupleUtils.function ((tx, account) ->  {
                    if (account.revertPay(tx)) {
                        statePort.update(account).subscribe();
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment of number " + account.getLastFourDigits());
                    }

                    processor.output().send(message(tx.withId()));

                    return tx.getTransactionId();
                }))
                ;
    }


    @Override
    public Mono<String> post(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return null;
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
