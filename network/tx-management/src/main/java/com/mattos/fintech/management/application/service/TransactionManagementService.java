package com.mattos.fintech.management.application.service;

import com.mattos.fintech.management.input.port.AuthenticateCreditCard;
import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;
import com.mattos.fintech.management.input.port.RequestApproval;
import com.mattos.fintech.management.input.port.TransactionInfo;
import reactor.core.publisher.Mono;

public class TransactionManagementService implements RequestApproval, AuthenticateCreditCard {


    @Override
    public Mono<PurchaseApprovalCommand> requestAuthentication(Mono<PurchaseApprovalCommand> command) {
        return null;
    }

    @Override
    public Mono<TransactionInfo> forwardApprovalRequest(Mono<PurchaseApprovalCommand> command) {
        return command.map(purchase -> TransactionInfo.builder()
                .amount(purchase.getAmount())
                .creditCardNumber(purchase.getCreditCardNumber())
                .originationId(purchase.getPurchaseId()).build());
    }
}
