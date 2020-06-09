package com.mattos.fintech.bank.domain.account.builder;

import com.mattos.fintech.bank.domain.account.CheckingAccount;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.banking.BankingAccountRequestInfo;

import static com.mattos.fintech.bank.domain.account.AccountType.CHECKING;

public class CheckingAccountBuilder extends AccountBuilder {

    public CheckingAccount build(BankingAccountRequestInfo request){

        AccountHolder accountHolder = new Person(request.getFirstName(), request.getLastName(), request.getAccountHolderId());

        return ((CheckingAccount)CHECKING.getInstance(request.getAccountName(), accountHolder));
    }

}
