package com.mattos.fintech.authorization.input.port;

import reactor.core.publisher.Mono;

public interface AuthorizeTransaction {

    Mono<TransactionInfo> evaluate(Mono<TransactionInfo> authenticationRequestInfo);

}
