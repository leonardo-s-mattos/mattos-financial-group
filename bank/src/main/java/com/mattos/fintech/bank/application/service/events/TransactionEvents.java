package com.mattos.fintech.bank.application.service.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public class TransactionEvents {


    public interface NewTransactionEvent {
        String INPUT = "newTransactionNotification";
        String OUTPUT = "newTransactionProcess";

        @Input(INPUT)
        MessageChannel input();

        @Output
        MessageChannel output();

    }

}