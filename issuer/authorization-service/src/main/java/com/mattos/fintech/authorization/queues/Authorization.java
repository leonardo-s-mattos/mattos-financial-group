package com.mattos.fintech.authorization.queues;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {


    String TX_AUTHORIZATION_REQUEST = "tx_authorization_request";
    String TX_AUTHORIZATION_RESPONSE = "tx_authorization_response";

    @Input(TX_AUTHORIZATION_REQUEST) SubscribableChannel txAuthorizationRequest();
    @Output(TX_AUTHORIZATION_RESPONSE) SubscribableChannel txAuthorizationResponse();

}
