package com.mattos.fintech.management.input.port;

import reactor.core.publisher.Mono;

public interface AuthenticateCreditCard {

    Mono<PurchaseApprovalCommand> requestAuthentication(Mono<PurchaseApprovalCommand> command);

}
