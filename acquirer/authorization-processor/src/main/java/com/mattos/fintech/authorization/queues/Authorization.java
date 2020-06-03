package com.mattos.fintech.authorization.queues;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {

    String AUTHORIZATION_INQUIRY = "authorization_inquiry";
    String AUTHORIZATION_REQUEST = "authorization_request";

    @Input(AUTHORIZATION_INQUIRY)
    SubscribableChannel autorizationInquiry();

    @Output(AUTHORIZATION_REQUEST)
    SubscribableChannel autorizationRequest();
}
