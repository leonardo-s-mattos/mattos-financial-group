package com.mattos.fintech.authentication.queues;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Authorization {


    String AUTHENTICATION_REQUEST = "authentication_request";
    String AUTHENTICATION_RESPONSE = "authentication_response";


    @Input(AUTHENTICATION_REQUEST) SubscribableChannel authenticationRequest();

    @Output(AUTHENTICATION_RESPONSE) SubscribableChannel authenticationResponse();




}
