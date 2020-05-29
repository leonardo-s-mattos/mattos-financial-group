package com.mattos.fintech.bank.application.service;

import static com.mattos.fintech.bank.domain.account.AccountType.CREDIT_CARD;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.domain.account.AccountType;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.account.builder.AccountBuilder;
import com.mattos.fintech.bank.domain.account.builder.CreditCardAccountBuilder;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.OpenAccount;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OpenAccountService implements OpenAccount {

    private CreditCardAccountStatePort creditCardStatePort;

    @Autowired
    public OpenAccountService(CreditCardAccountStatePort creditCardStatePort) {
        this.creditCardStatePort = creditCardStatePort;
    }

    @Override
    public Mono<String> openCreditCardAccount(CreditCardRequestInfo request) {

        CreditCardAccount newAccount = ((CreditCardAccountBuilder)AccountBuilder.init().get(CREDIT_CARD))
                .build(request);

        Mono<CreditCardAccount> savedAccount = creditCardStatePort.create(newAccount.withAccountNumber());

        return savedAccount.map(Account::getLastFourDigits);
    }
}
