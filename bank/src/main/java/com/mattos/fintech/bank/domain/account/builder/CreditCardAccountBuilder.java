package com.mattos.fintech.bank.domain.account.builder;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        return CreditCardAccount.builder().currentBalance(BigDecimal.ZERO)
                .accountHolder(accountHolder)
                .accountType(CREDIT_CARD).name(request.getAccountName()).goodThroughMonth(LocalDate.now().getYear() + 4)
                .goodThroughMonth(LocalDate.now().getMonthValue())
                .ccvCode(new Random().nextInt(1000)).limitAmount(BigDecimal.valueOf(5000L))
                .issuerCompany(IssuerCompany.valueOf(request.getIssuerCompany()))
                .build();
    }

}
