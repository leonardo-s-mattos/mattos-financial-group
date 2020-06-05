package com.mattos.fintech.management.input.adapter;

import com.mattos.fintech.management.input.port.*;
import com.mattos.fintech.management.queues.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EventsListener {

    private RequestApproval requestApprovalPort;
    private RegisterNewTransaction registerNewTransactionPort;
    private ProcessAuthorizationResponse processAuthorizationResponsePort;

    @Autowired
    public EventsListener(RequestApproval requestApprovalPort
            , RegisterNewTransaction registerNewTransactionPort
            , ProcessAuthorizationResponse processAuthorizationResponsePort) {
        this.requestApprovalPort = requestApprovalPort;
        this.registerNewTransactionPort = registerNewTransactionPort;
        this.processAuthorizationResponsePort = processAuthorizationResponsePort;
    }

    @Transformer(inputChannel = Authorization.AUTHORIZATION_REQUEST,
            outputChannel = Authorization.TX_AUTHORIZATION_REQUEST)
    public TransactionInfo forwardApprovalRequest(PurchaseApprovalCommand command) {
        return requestApprovalPort.forwardApprovalRequest(Mono.just(command)).block();
    }


    @StreamListener(Authorization.AUTHORIZATION_REQUEST)
    public void registerTransaction(PurchaseApprovalCommand purchaseApprovalCommand){
        registerNewTransactionPort.registerNewTransaction(purchaseApprovalCommand);

    }

    @Transformer(inputChannel = Authorization.TX_AUTHORIZATION_RESPONSE,
            outputChannel = Authorization.AUTHENTICATION_REQUEST)
    public AuthenticationInfo processAuthorizationGranted(TransactionInfo response) {
        return processAuthorizationResponsePort.transactionApproved(response);
    }

    @Transformer(inputChannel = Authorization.TX_AUTHORIZATION_RESPONSE,
            outputChannel = Authorization.AUTHORIZATION_RESPONSE)
    public TransactionInfo processAuthorizationDeclined(TransactionInfo response) {
        return processAuthorizationResponsePort.transactionDeclined(response);
    }
}
