package com.mattos.fintech.management.application.service;

import com.mattos.fintech.management.domain.CreditCardTransaction;
import com.mattos.fintech.management.domain.TransactionStatus;
import com.mattos.fintech.management.input.port.AuthenticationInfo;
import com.mattos.fintech.management.input.port.ProcessAuthorizationResponse;
import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;
import com.mattos.fintech.management.input.port.TransactionInfo;
import com.mattos.fintech.management.output.port.CreditCardTransactionRepoPort;
import com.mattos.fintech.management.output.port.TemporaryCacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ProcessAuthorizationResponseService implements ProcessAuthorizationResponse {

    private CreditCardTransactionRepoPort creditCardTransactionRepoPort;
    private TemporaryCacheRepo cacheRepo;

    @Autowired
    public CreditCardTransactionRepoPort getCreditCardTransactionRepoPort() {
        return creditCardTransactionRepoPort;
    }

    public void setCreditCardTransactionRepoPort(CreditCardTransactionRepoPort creditCardTransactionRepoPort) {
        this.creditCardTransactionRepoPort = creditCardTransactionRepoPort;
    }

    @Override
    public TransactionInfo transactionDeclined(TransactionInfo response) {

        Mono<CreditCardTransaction> retrievedTransaction =
                creditCardTransactionRepoPort.findByOriginationId(response.getOriginationId());

        retrievedTransaction.map(transaction ->
                creditCardTransactionRepoPort.save(transaction.withStatus(TransactionStatus.DECLINED)).block());

        return response;
    }

    @Override
    public AuthenticationInfo transactionApproved(TransactionInfo response) {

        Optional<PurchaseApprovalCommand> originalPurchaseApproval = cacheRepo.pullFromCache(response.getOriginationId());

        if(originalPurchaseApproval.isPresent()){
            return AuthenticationInfo.fromPurchaseApprovalCommand(originalPurchaseApproval.get());
        } else {
            throw new RuntimeException("Error on processing authorization response : fail tor retrieve original purchase data");
        }

    }
}
