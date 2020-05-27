package com.mattos.fintech.bank.domain.account;

import com.google.common.base.Strings;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import org.apache.commons.lang.StringUtils;

public abstract class Account {

    private String accountNumber;
    private AccountType accountType;
    private AccountState state;

    private String name;
    private AccountHolder accountHolder;

    public Account(AccountType accountType, AccountState state, String name, AccountHolder accountHolder) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.state = state;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    protected void withNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    protected String getAccountNumber(){
        return accountNumber;
    }

    public String getLastFourDigits(){
        return Strings.repeat("*", accountNumber.length()-4) + accountNumber.substring(accountNumber.length()-4);
    }


}
