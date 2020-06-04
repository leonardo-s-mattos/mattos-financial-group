package com.mattos.fintech.management.input.port;

import reactor.core.publisher.Mono;

public interface RequestApproval {

    Mono<PurchaseApprovalCommand> forwardApprovalRequest(Mono<PurchaseApprovalCommand> command);
}
