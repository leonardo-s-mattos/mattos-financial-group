package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.domain.account.builder.AccountBuilder;
import com.mattos.fintech.bank.domain.account.builder.CheckingAccountBuilder;
import com.mattos.fintech.bank.domain.account.builder.CreditCardAccountBuilder;
import com.mattos.fintech.bank.domain.account.builder.SavingsAccountBuilder;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.query.port.BankingAccountInfo;
import com.mattos.fintech.bank.input.usecase.port.BankingAccountRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.OpenAccount;
import com.mattos.fintech.bank.output.port.CheckingAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.SavingsAccountStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.mattos.fintech.bank.domain.account.AccountType.*;

@Component
public class OpenAccountService implements OpenAccount {

    private CreditCardAccountStatePort creditCardStatePort;
    private CheckingAccountStatePort checkingStatePort;
    private SavingsAccountStatePort savingsStatePort;

    @Autowired
    public OpenAccountService(CreditCardAccountStatePort creditCardStatePort,
                                CheckingAccountStatePort checkingStatePort,
                                SavingsAccountStatePort savingsStatePort) {
        this.creditCardStatePort = creditCardStatePort;
        this.checkingStatePort = checkingStatePort;
        this.savingsStatePort = savingsStatePort;
    }

    @Override
    public Mono<String> openCreditCardAccount(CreditCardRequestInfo request) {

        CreditCardAccount newAccount = ((CreditCardAccountBuilder)AccountBuilder.init().get(CREDIT_CARD))
                .build(request);

        Mono<CreditCardAccount> savedAccount = creditCardStatePort.create(newAccount.withAccountNumber());

        return savedAccount.map(Account::getLastFourDigits);
    }

    @Override
    public Mono<String> openSavingsAccount(BankingAccountRequestInfo request) {

        SavingsAccount newAccount = ((SavingsAccountBuilder)AccountBuilder.init().get(SAVINGS))
                .build(request);

        Mono<SavingsAccount> savedAccount = savingsStatePort.create(newAccount.withAccountNumber());

        return savedAccount.map(Account::getLastFourDigits);
    }

    @Override
    public Mono<String> openCheckingAccount(BankingAccountRequestInfo request) {

        CheckingAccount newAccount = ((CheckingAccountBuilder)AccountBuilder.init().get(CHECKING))
                .build(request);

        Mono<CheckingAccount> savedAccount = checkingStatePort.create(newAccount.withAccountNumber());

        return savedAccount.map(Account::getLastFourDigits);
    }
}
