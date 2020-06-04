package com.mattos.fintech.management.application.service;

import com.mattos.fintech.management.domain.CreditCardTransaction;
import com.mattos.fintech.management.domain.TransactionStatus;
import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;
import com.mattos.fintech.management.output.port.CreditCardTransactionRepoPort;
import com.mattos.fintech.management.queues.Authorization;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionPersistenceService {

    private CreditCardTransactionRepoPort creditCardTransactionRepoPort;

    public TransactionPersistenceService(CreditCardTransactionRepoPort creditCardTransactionRepoPort) {
        this.creditCardTransactionRepoPort = creditCardTransactionRepoPort;
    }

    @StreamListener(Authorization.AUTHORIZATION_REQUEST)
    public void listenNewTransaction(PurchaseApprovalCommand command){

        CreditCardTransaction transaction = CreditCardTransaction.builder()
                .transactionStatus(TransactionStatus.PENDING)
                .acquirerBank(command.getAcquirerBank())
                .creditCardNumber(command.getCreditCardNumber())
                .issuerBank(command.getIssuerBank())
                .id(UUID.randomUUID())
                .originalAmount(command.getAmount())
                .originationId(command.getPurchaseId())
                .time(command.getTime())
                .build();

        creditCardTransactionRepoPort.save(transaction);



    }
}
