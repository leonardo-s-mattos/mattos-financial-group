package com.mattos.fintech.bank.input.usecase.port;

import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import reactor.core.publisher.Mono;

public interface OpenAccount {

    public Mono<String> openCreditCardAccount(CreditCardRequestInfo request);
}
