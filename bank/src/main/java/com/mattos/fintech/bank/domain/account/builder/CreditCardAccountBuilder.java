package com.mattos.fintech.bank.domain.account.builder;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;

import java.util.Random;

import static com.mattos.fintech.bank.domain.account.AccountType.CREDIT_CARD;

public class CreditCardAccountBuilder extends AccountBuilder {

    public CreditCardAccount build(CreditCardRequestInfo request){

        AccountHolder accountHolder = new Person(request.getFirstName(), request.getLastName(), request.getAccountHolderId())
                .withBillingAddress(request.getStreetAddress(),
                        request.getCity(),
                        request.getState(),
                        request.getCountry(),
                        request.getZipCode());

        return ((CreditCardAccount)CREDIT_CARD.getInstance(request.getAccountName(), accountHolder))
                .withIssuer(IssuerCompany.valueOf(request.getIssuerCompany()));
    }

}
