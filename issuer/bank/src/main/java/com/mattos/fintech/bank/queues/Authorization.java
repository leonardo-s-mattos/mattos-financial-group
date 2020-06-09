package com.mattos.fintech.bank.queues;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {

    String TX_AUTHORIZATION_RESPONSE = "tx_authorization_response";
    String AUTHENTICATION_RESPONSE = "authentication_response";

    @Input(TX_AUTHORIZATION_RESPONSE) SubscribableChannel txAuthorizationResponse();
    @Input(AUTHENTICATION_RESPONSE) SubscribableChannel authenticationResponse();

}
