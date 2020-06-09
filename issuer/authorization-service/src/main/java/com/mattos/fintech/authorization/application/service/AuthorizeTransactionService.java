package com.mattos.fintech.authorization.application.service;

import com.mattos.fintech.authorization.domain.CreditCard;
import com.mattos.fintech.authorization.input.port.AuthorizeTransaction;
import com.mattos.fintech.authorization.input.port.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@Component
public class AuthorizeTransactionService implements AuthorizeTransaction {

    private WebClient.Builder webClientBuilder;

    @Autowired
    public AuthorizeTransactionService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<TransactionInfo> evaluate(Mono<TransactionInfo> transactionRequestInfo){

            return transactionRequestInfo.zipWith(

                    webClientBuilder.build().get().uri("https://bank/accounts/creditcards/{creditCardNumber}",
                            transactionRequestInfo.block()).retrieve().bodyToMono(CreditCard.class))

                    .map(TupleUtils.function((request, card) -> {
                        return TransactionInfo.builder().creditCardNumber(request.getCreditCardNumber())
                                .amount(request.getAmount())
                                .originationId(request.getOriginationId())
                                .purchaseStatus(
                                        card.getCurrentBalance().add(request.getAmount()).compareTo(card.getLimitAmount())
                                        >0?"DECLINED":"APPROVED").build();
                    }));
    }
}
