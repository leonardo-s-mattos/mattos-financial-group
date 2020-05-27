package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

public enum AccountType {

        CREDIT_CARD {
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
                @Override
                public CreditCardAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return new CreditCardAccount(accountNumber, name, accountHolder);
                }
        },
        SAVINGS{
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
                @Override
                public CreditCardAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return new CreditCardAccount(accountNumber, name, accountHolder);
                }
        },
        CHECKING{
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return new CreditCardAccount(name, accountHolder);
                }
                @Override
                public CreditCardAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return new CreditCardAccount(accountNumber, name, accountHolder);
                }
        };

        public abstract Account getInstance(String name, AccountHolder accountHolder);
        public abstract Account getInstance(String accountNumber, String name, AccountHolder accountHolder);
}
