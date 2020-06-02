package com.mattos.fintech.bank.application.service.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public class TransactionEvents {


    public interface NewTransactionEvent {
        String NEW_TRANSACTION = "newTransactionNotification";
        String LOGGED_TRANSACTION_NOTIFICATION = "loggedTransactionNotification";

        @Input(NEW_TRANSACTION)
        MessageChannel newTransactionNotification();

        @Output(LOGGED_TRANSACTION_NOTIFICATION)
        MessageChannel loggedTransactionNotification();

    }

}