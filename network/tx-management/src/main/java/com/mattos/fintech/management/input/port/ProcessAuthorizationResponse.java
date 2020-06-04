package com.mattos.fintech.management.input.port;

public interface ProcessAuthorizationResponse {

    TransactionInfo transactionDeclined(TransactionInfo response);
    AuthenticationInfo transactionApproved(TransactionInfo response);
}
