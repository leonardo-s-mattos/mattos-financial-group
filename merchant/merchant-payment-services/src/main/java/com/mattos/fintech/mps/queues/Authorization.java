package com.mattos.fintech.mps.queues;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {

    String AUTHORIZATION_INQUIRY = "authorization_inquiry";

    @Output(AUTHORIZATION_INQUIRY)
    SubscribableChannel autorizationInquiry();

}
