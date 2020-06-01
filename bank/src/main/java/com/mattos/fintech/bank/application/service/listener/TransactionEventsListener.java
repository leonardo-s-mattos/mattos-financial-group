package com.mattos.fintech.bank.application.service.listener;


import com.mattos.fintech.bank.application.service.events.TransactionEvents.NewTransactionEvent;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(NewTransactionEvent.class)
@Slf4j
public class TransactionEventsListener {

    @StreamListener(NewTransactionEvent.OUTPUT)
    public void listenTo(CreditCardPayment tx){
        log.info("Saving Transaction");
    }
}
