package com.mattos.fintech.authorization.input.adapter;

import com.mattos.fintech.authorization.input.port.AuthorizeTransaction;
import com.mattos.fintech.authorization.input.port.TransactionInfo;
import com.mattos.fintech.authorization.queues.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationRequestListener {

    private AuthorizeTransaction port;

    @Autowired
    public AuthorizationRequestListener(AuthorizeTransaction port) {
        this.port = port;
    }

    @Transformer(inputChannel = Authorization.TX_AUTHORIZATION_REQUEST,
            outputChannel = Authorization.TX_AUTHORIZATION_RESPONSE)
    public TransactionInfo listen(TransactionInfo command) {
        return port.evaluate(Mono.just(command)).block();
    }
}
