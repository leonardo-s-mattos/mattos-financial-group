package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.application.service.exception.InvalidTransactionRequestException;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.domain.transaction.TransactionStatus;
import com.mattos.fintech.bank.input.usecase.port.PayCreditCard;
import com.mattos.fintech.bank.input.usecase.port.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@Component
public class PayCreditCardService implements PayCreditCard {

    private CreditCardQueryPort queryPort;
    private CreditCardAccountStatePort statePort;

    private Flux<Transaction> newPendingCreditCardPayments;

    @Autowired
    public PayCreditCardService(CreditCardQueryPort queryPort, CreditCardAccountStatePort statePort) {
        this.queryPort = queryPort;
        this.statePort = statePort;

        newPendingCreditCardPayments = Flux.empty();
    }

    @Override
    public Mono<String> pay(Mono<TransactionRequestInfo> transactionRequestInfo) {


        return Mono.zip(transactionRequestInfo.map(requestInfo ->
                        (CreditCardPayment)CreditCardPayment.builder().transactionDate(requestInfo.getTransactionDate())
                            .amount(requestInfo.getAmount())
                            .status(TransactionStatus.PENDING).build()),
                transactionRequestInfo.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccountNumber()).block()),

                Mono.just(false))

                .map(TupleUtils.function ((tx, account, result) ->  {
                    if (account.pay(tx)) {
                        statePort.update(account);
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment of number " + account.getLastFourDigits());
                    }

                    newPendingCreditCardPayments = Flux.just(tx.withId()).publish();

                    return tx.getTransactionId();
                }))
                ;

    }

    @Override
    public Mono<String> revert(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return null;
    }

    @Override
    public Mono<String> post(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return null;
    }

    public Flux<Transaction> newTransactionStream() {
        return newPendingCreditCardPayments;
    }
}
