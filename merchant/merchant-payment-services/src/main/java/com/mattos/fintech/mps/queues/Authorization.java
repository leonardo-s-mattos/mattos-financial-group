package com.mattos.fintech.mps.queues;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {

    String AUTHORIZATION_INQUIRY = "authorization_inquiry";
    String CONFIRMED_PURCHASE = "confirmed_purchase";

    @Output(AUTHORIZATION_INQUIRY)
    SubscribableChannel autorizationInquiry();

    @Output(CONFIRMED_PURCHASE)
    SubscribableChannel confirmedPurchase();

}
