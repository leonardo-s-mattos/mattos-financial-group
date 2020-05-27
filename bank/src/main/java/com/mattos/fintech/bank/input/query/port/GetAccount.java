package com.mattos.fintech.bank.input.query.port;

import reactor.core.publisher.Flux;

public interface GetAccount {

    Flux<CreditCardInfo> listAllOpenCards(String accountHolderId);

}
