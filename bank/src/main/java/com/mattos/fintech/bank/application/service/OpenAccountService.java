package com.mattos.fintech.bank.application.service;

import static com.mattos.fintech.bank.domain.account.AccountType.CREDIT_CARD;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.OpenAccount;
import com.mattos.fintech.bank.output.port.AccountState;
import reactor.core.publisher.Mono;

public class OpenAccountService implements OpenAccount {

    private AccountState accountStatePort;

    public OpenAccountService(AccountState accountStatePort) {
        this.accountStatePort = accountStatePort;
    }

    @Override
    public Mono<String> openCreditCardAccount(CreditCardRequestInfo request) {

        AccountHolder accountHolder = new Person(request.getFirstName(), request.getLastName(), request.getAccountHolderId());
        CreditCardAccount newAccount = (CreditCardAccount)CREDIT_CARD.getInstance(request.getAccountName(), accountHolder);
        newAccount.withIssuer(IssuerCompany.valueOf(request.getIssuerCompany()));

        Mono<? extends Account> savedAccount = accountStatePort.createAccount(newAccount);

        return savedAccount.map(Account::getLastFourDigits);
    }


}
