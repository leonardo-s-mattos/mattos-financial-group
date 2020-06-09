package com.mattos.fintech.authentication.input.port;

import reactor.core.publisher.Mono;

public interface AuthenticateTransaction {

    Mono<AuthenticationInfo> evaluate(Mono<AuthenticationInfo> authenticationRequestInfo);

}
