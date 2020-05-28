package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.query.port.GetAccount;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class QueryAccountsService implements GetAccount{

    private final CreditCardQueryPort port;

    public QueryAccountsService(CreditCardQueryPort port) {
        this.port = port;
    }


    @Override
    public Flux<CreditCardInfo> listAllOpenCards(String accountHolderId) {
        return port.listAllCreditCards(accountHolderId)
                .map( creditCard -> new CreditCardInfo(creditCard.getAccountNumber(),
                        creditCard.getLastFourDigits(),
                        creditCard.getGoodThroughMonth(),
                        creditCard.getGoodThroughYear()));
    }
}
