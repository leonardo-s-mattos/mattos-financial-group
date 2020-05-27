package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

import java.math.BigDecimal;

public class CheckingAccount extends Account{

    private BigDecimal currentBalance;

    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }

    public CheckingAccount(String name, AccountHolder accountHolder) {
        super(AccountType.CHECKING, name, accountHolder);
        currentBalance = BigDecimal.ZERO;
    }

}
