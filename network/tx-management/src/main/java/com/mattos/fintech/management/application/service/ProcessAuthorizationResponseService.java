package com.mattos.fintech.management.application.service;

import com.mattos.fintech.management.input.port.AuthenticationInfo;
import com.mattos.fintech.management.input.port.ProcessAuthorizationResponse;
import com.mattos.fintech.management.input.port.TransactionInfo;
import org.springframework.stereotype.Component;

@Component
public class ProcessAuthorizationResponseService implements ProcessAuthorizationResponse {
    @Override
    public TransactionInfo transactionDeclined(TransactionInfo response) {
        return null;
    }

    @Override
    public AuthenticationInfo transactionApproved(TransactionInfo response) {
        return null;
    }
}
