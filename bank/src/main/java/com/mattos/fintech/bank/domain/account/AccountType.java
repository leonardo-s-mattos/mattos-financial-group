package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

import java.math.BigDecimal;

public enum AccountType {

        CREDIT_CARD {
                @Override
                public CreditCardAccount getInstance(String name, AccountHolder accountHolder){
                        return CreditCardAccount.builder().name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
                @Override
                public CreditCardAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return CreditCardAccount.builder().accountNumber(accountNumber).name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
        },
        SAVINGS{
                @Override
                public SavingsAccount getInstance(String name, AccountHolder accountHolder){
                        return SavingsAccount.builder().name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
                @Override
                public SavingsAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return SavingsAccount.builder().accountNumber(accountNumber).name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
        },
        CHECKING{
                @Override
                public CheckingAccount getInstance(String name, AccountHolder accountHolder){
                        return CheckingAccount.builder().name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
                @Override
                public CheckingAccount getInstance(String accountNumber, String name, AccountHolder accountHolder){
                        return CheckingAccount.builder().accountNumber(accountNumber).name(name).
                                accountHolder(accountHolder).currentBalance(BigDecimal.ZERO).build();
                }
        };

        public abstract Account getInstance(String name, AccountHolder accountHolder);
        public abstract Account getInstance(String accountNumber, String name, AccountHolder accountHolder);

}
