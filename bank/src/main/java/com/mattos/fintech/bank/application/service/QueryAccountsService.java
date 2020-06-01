package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.input.query.port.BankingAccountInfo;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.query.port.GetAccount;
import com.mattos.fintech.bank.output.port.CheckingAccountQueryPort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.SavingsAccountQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class QueryAccountsService implements GetAccount{

    private final CreditCardQueryPort creditCardPort;
    private final SavingsAccountQueryPort savingsAccountQueryPort;
    private final CheckingAccountQueryPort checkingAccountQueryPort;

    @Autowired
    public QueryAccountsService(CreditCardQueryPort creditCardPort,
                                CheckingAccountQueryPort checkingAccountQueryPort,
                                SavingsAccountQueryPort savingsAccountQueryPort) {
        this.creditCardPort = creditCardPort;
        this.savingsAccountQueryPort = savingsAccountQueryPort;
        this.checkingAccountQueryPort = checkingAccountQueryPort;
    }


    @Override
    public Flux<CreditCardInfo> listAllOpenCards(String accountHolderId) {
        return creditCardPort.listAllCreditCards(accountHolderId)
                .map( creditCard ->  CreditCardInfo.builder().creditCardNumber(creditCard.getAccountNumber()).
                        creditCardLastDigits(creditCard.getLastFourDigits()).
                        goodThoughMonth(creditCard.getGoodThroughMonth()).
                        goodThoughMonth(creditCard.getGoodThroughYear()).
                        currentBalance(creditCard.getCurrentBalance()).build());
    }

    @Override
    public Flux<BankingAccountInfo> listAllOpenCheckingAccounts(String accountHolderId) {
        return checkingAccountQueryPort.listAllAccounts(accountHolderId)
                .map( checking -> new BankingAccountInfo(checking.getAccountNumber(),
                        checking.getLastFourDigits(), checking.getCurrentBalance(), checking.getAccountType().name()));
    }

    @Override
    public Flux<BankingAccountInfo> listAllOpenSavingsAccounts(String accountHolderId) {
        return savingsAccountQueryPort.listAllAccounts(accountHolderId)
                .map( savings -> new BankingAccountInfo(savings.getAccountNumber(),
                        savings.getLastFourDigits(),
                        savings.getCurrentBalance(),
                        savings.getAccountType().name()));
    }
}
