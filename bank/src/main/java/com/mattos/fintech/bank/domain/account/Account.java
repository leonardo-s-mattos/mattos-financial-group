package com.mattos.fintech.bank.domain.account;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import org.apache.commons.lang.StringUtils;

public abstract class Account {

    private String accountNumber;
    private AccountType accountType;
    private AccountState state;

    private String name;
    private AccountHolder accountHolder;

    public Account(AccountType accountType, String name, AccountHolder accountHolder) {
        this.accountType = accountType;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    public Account(String accountNumber, AccountType accountType, String name, AccountHolder accountHolder) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    protected void withNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public Account withState(AccountState state){
        this.state = state;
        return this;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getLastFourDigits(){
        return Strings.repeat("*", accountNumber.length()-4) + accountNumber.substring(accountNumber.length()-4);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equal(accountNumber, account.accountNumber) &&
                Objects.equal(accountHolder, account.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber, accountHolder);
    }
}
