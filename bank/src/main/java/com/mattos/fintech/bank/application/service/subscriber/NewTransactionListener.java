package com.mattos.fintech.bank.application.service.subscriber;

import com.mattos.fintech.bank.application.service.PayCreditCardService;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.input.usecase.port.PayCreditCard;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class NewTransactionListener implements Subscriber<Transaction> {

    private PayCreditCardService payCreditCardStreamEvent;

    @Autowired
    public NewTransactionListener(PayCreditCardService payCreditCardStreamEvent) {
        this.payCreditCardStreamEvent = payCreditCardStreamEvent;
        payCreditCardStreamEvent.newTransactionStream().subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Save new transaction");
    }

    @Override
    public void onNext(Transaction transaction) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
