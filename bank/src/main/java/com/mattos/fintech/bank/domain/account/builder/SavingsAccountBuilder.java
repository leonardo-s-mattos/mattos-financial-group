package com.mattos.fintech.bank.domain.account.builder;

import com.mattos.fintech.bank.domain.account.SavingsAccount;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.banking.BankingAccountRequestInfo;

import static com.mattos.fintech.bank.domain.account.AccountType.SAVINGS;

public class SavingsAccountBuilder extends AccountBuilder {

    public SavingsAccount build(BankingAccountRequestInfo request){

        AccountHolder accountHolder = new Person(request.getFirstName(), request.getLastName(), request.getAccountHolderId());

        return ((SavingsAccount)SAVINGS.getInstance(request.getAccountName(), accountHolder));
    }

}
