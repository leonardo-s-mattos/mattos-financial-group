package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.query.port.GetAccount;
import com.mattos.fintech.bank.output.port.AccountQuery;
import reactor.core.publisher.Flux;

import java.util.List;

public class QueryAccountsService implements GetAccount{

    private final AccountQuery port;

    public QueryAccountsService(AccountQuery port) {
        this.port = port;
    }


    @Override
    public Flux<CreditCardInfo> listAllOpenCards(String accountHolderId) {
        return port.listAllCreditCards(accountHolderId)
                .map( account -> new CreditCardInfo(account.getAccountNumber(),
                        account.getLastFourDigits(),
                        ((CreditCardAccount)account).getGoodThroughMonth(),
                        ((CreditCardAccount)account).getGoodThroughYear()));
    }
}
