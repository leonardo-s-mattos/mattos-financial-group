package com.mattos.fintech.bank.application.service.banking;

import com.mattos.fintech.bank.application.service.events.TransactionEvents.*;
import com.mattos.fintech.bank.application.service.exception.InvalidTransactionRequestException;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.input.usecase.port.banking.PayCreditCard;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.function.TupleUtils;

@Component
@EnableBinding(NewTransactionEvent.class)
public class PayCreditCardService implements PayCreditCard {

    private CreditCardQueryPort queryPort;
    private CreditCardAccountStatePort statePort;



    @Autowired
    public PayCreditCardService(CreditCardQueryPort queryPort, CreditCardAccountStatePort statePort) {
        this.queryPort = queryPort;
        this.statePort = statePort;
    }

    @Override
    @StreamListener(target = NewTransactionEvent.LOGGED_TRANSACTION_NOTIFICATION,
    condition = "payload.transactionType.name=='CREDIT_CARD_PAYMENT'")
    public Flux<String> pay(Flux<Transaction> transactionRequest) {

        return Flux.zip(transactionRequest.map(tx -> (CreditCardPayment)tx),
                        transactionRequest.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccount()).block()))

                .map(TupleUtils.function ((tx, account) ->  {

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
    @StreamListener(target = NewTransactionEvent.LOGGED_TRANSACTION_NOTIFICATION,
            condition = "payload.transactionType.name=='CREDIT_CARD_PAYMENT_CANCELLED'")
    public Flux<String> revert(Flux<Transaction> transactionRequest) {
        return Flux.zip(transactionRequest.map(tx -> (CreditCardPayment)tx),
                transactionRequest.map(requestInfo -> queryPort.findById(requestInfo.getTargetAccount()).block()))

                .map(TupleUtils.function ((tx, account) ->  {

                    if (account.revertPay(tx)) {
                        statePort.update(account).subscribe();
                    } else {
                        throw new InvalidTransactionRequestException("There was an error registering the Credit Card payment of number " + account.getLastFourDigits());
                    }

                    return tx.getTransactionId();
                }))
                ;

    }

    @Override
    public Flux<String> post(Flux<Transaction> transactionRequest) {
        return null;
    }



}
