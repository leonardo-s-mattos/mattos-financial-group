package com.mattos.fintech.management.queues;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {

    String AUTHORIZATION_REQUEST = "authorization_request";
    String AUTHORIZATION_RESPONSE = "authorization_response";
    String TX_AUTHORIZATION_REQUEST = "tx_authorization_request";
    String TX_AUTHORIZATION_RESPONSE = "tx_authorization_response";
    String AUTHENTICATION_REQUEST = "authentication_request";
    String AUTHENTICATION_RESPONSE = "authentication_response";

    @Input(AUTHORIZATION_REQUEST) SubscribableChannel authorizationRequest();

    @Input(TX_AUTHORIZATION_RESPONSE) SubscribableChannel txAuthorizationResponse();

    @Input(AUTHENTICATION_RESPONSE) SubscribableChannel authenticationResponse();

    @Output(AUTHORIZATION_RESPONSE) SubscribableChannel authorizationResponse();

    @Output(TX_AUTHORIZATION_REQUEST) SubscribableChannel txAuthorizationRequest();

    @Output(AUTHENTICATION_REQUEST) SubscribableChannel authenticationRequest();


}
