package com.mattos.fintech.bank.application.service.banking;

import com.mattos.fintech.bank.application.service.exception.InvalidTransactionRequestException;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.TransactionStatus;
import com.mattos.fintech.bank.input.usecase.port.banking.PayCreditCard;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.TransactionLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

import static reactor.function.TupleUtils.function;

@Component
public class PayCreditCardService implements PayCreditCard {

    private CreditCardQueryPort queryPort;
    private CreditCardAccountStatePort statePort;
    private TransactionLogPort transactionLogPort;

    @Autowired
    public PayCreditCardService(CreditCardQueryPort queryPort, CreditCardAccountStatePort statePort, TransactionLogPort transactionLogPort) {
        this.queryPort = queryPort;
        this.statePort = statePort;
        this.transactionLogPort = transactionLogPort;
    }

    @Override
    public Mono<String> pay(Mono<TransactionRequestInfo> transactionRequestInfo) {


        return Mono.zip(transactionRequestInfo.map(requestInfo ->
                        (CreditCardPayment)requestInfo.getTransactionType().builder()
                                .transactionDate(requestInfo.getTransactionDate())
                                .amount(requestInfo.getAmount())
                                .targetAccount(requestInfo.getTargetAccountNumber())
                                .status(TransactionStatus.PENDING)
                                .transactionType(requestInfo.getTransactionType()).build()),
                transactionRequestInfo.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccountNumber()).block()))

                .map(TupleUtils.function ((tx, account) ->  {

                    transactionLogPort.log(tx.withId()).subscribe();

                    if (account.pay(tx)) {
                        statePort.update(account).subscribe();
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment of number " + account.getLastFourDigits());
                    }

                    return tx.getTransactionId();
                }))
                ;

    }

    @Override
    public Mono<String> revert(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return Mono.zip(transactionRequestInfo.map(requestInfo ->
                        (CreditCardPayment)requestInfo.getTransactionType().builder()
                                .transactionDate(requestInfo.getTransactionDate())
                                .amount(requestInfo.getAmount())
                                .targetAccount(requestInfo.getTargetAccountNumber())
                                .status(TransactionStatus.PENDING)
                                .transactionType(requestInfo.getTransactionType()).build()),
                transactionRequestInfo.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccountNumber()).block()))

                .map(TupleUtils.function ((tx, account) ->  {

                    transactionLogPort.log(tx.withId()).subscribe();

                    if (account.revertPay(tx)) {
                        statePort.update(account).subscribe();
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment cancellation of number " + account.getLastFourDigits());
                    }

                    return tx.getTransactionId();
                }))
                ;
    }


    @Override
    public Mono<String> post(Mono<TransactionRequestInfo> transactionRequestInfo) {
        return null;
    }


}
