package com.mattos.fintech.authentication.input.adapter;

import com.mattos.fintech.authentication.input.port.AuthenticateTransaction;
import com.mattos.fintech.authentication.input.port.AuthenticationInfo;
import com.mattos.fintech.authentication.queues.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationRequestListener {

    private AuthenticateTransaction port;

    @Autowired
    public AuthenticationRequestListener(AuthenticateTransaction port) {
        this.port = port;
    }

    @Transformer(inputChannel = Authorization.AUTHENTICATION_REQUEST,
            outputChannel = Authorization.AUTHENTICATION_RESPONSE)
    public AuthenticationInfo listen(AuthenticationInfo command) {
        return port.evaluate(Mono.just(command)).block();
    }
}
