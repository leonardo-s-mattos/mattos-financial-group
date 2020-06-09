package com.mattos.fintech.authentication.service;

import com.mattos.fintech.authentication.domain.CreditCard;
import com.mattos.fintech.authentication.input.port.AuthenticateTransaction;
import com.mattos.fintech.authentication.input.port.AuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.function.TupleUtils;

@Component
public class AuthenticateTransactionService implements AuthenticateTransaction {

    private WebClient.Builder webClientBuilder;

    @Autowired
    public AuthenticateTransactionService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<AuthenticationInfo> evaluate(Mono<AuthenticationInfo> authenticationInfo){

            return authenticationInfo.zipWith(

                    webClientBuilder.build().get().uri("https://bank/accounts/creditcards/{creditCardNumber}",
                            authenticationInfo.block()).retrieve().bodyToMono(CreditCard.class))

                    .map(TupleUtils.function((request, card) -> {
                        return AuthenticationInfo.builder().creditCardNumber(request.getCreditCardNumber())
                                .originationId(request.getOriginationId())
                                .status(
                                        card.getCcvCode().equals(request.getCcvCode()) &&
                                        card.getGoodThoughMonth().equals(request.getGoodThroughMonth()) &&
                                        card.getGoodThoughYear().equals(request.getGoodThroughYear())
                                        ?"APPROVED":"DECLINED").build();
                    }));
    }
}
