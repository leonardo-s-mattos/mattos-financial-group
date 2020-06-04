package com.mattos.fintech.management.input.port;

import reactor.core.publisher.Mono;

public interface RequestApproval {

    Mono<TransactionInfo> forwardApprovalRequest(Mono<PurchaseApprovalCommand> command);
}
