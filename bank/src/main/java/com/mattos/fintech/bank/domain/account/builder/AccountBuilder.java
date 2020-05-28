package com.mattos.fintech.bank.domain.account.builder;

import com.mattos.fintech.bank.domain.account.AccountType;

import java.util.EnumMap;

public abstract class AccountBuilder {

    public static EnumMap builders;

    public static EnumMap init(){
        builders = new EnumMap<>(AccountType.class);

        builders.put(AccountType.CREDIT_CARD, new CreditCardAccountBuilder());
        builders.put(AccountType.SAVINGS, new SavingsAccountBuilder());
        builders.put(AccountType.CHECKING, new CheckingAccountBuilder());
        return builders;
    }




}
