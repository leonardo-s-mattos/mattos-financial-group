package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

public enum AccountType {

        CREDIT_CARD {
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
        },
        SAVINGS{
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
        },
        CHECKING{
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
        };

        public abstract Account getInstance(String name, AccountHolder accountHolder);
}
