package com.mattos.fintech.bank.domain.account;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayDeque;
import java.util.Optional;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
public abstract class Account {

    @Id
    protected String accountNumber;
    protected AccountType accountType;
    protected AccountState state;

    protected String name;
    protected AccountHolder accountHolder;

    @Transient
    protected ArrayDeque<Transaction> recentTransactions = new ArrayDeque<>();

    protected void withNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public Account withState(AccountState state){
        this.state = state;
        return this;
    }

    public String getLastFourDigits(){
        return Strings.repeat("*", accountNumber.length()-4) + accountNumber.substring(accountNumber.length()-4);
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

    protected void addTransaction(Transaction newTransaction){
        if(recentTransactions==null) recentTransactions = new ArrayDeque<>();
        this.recentTransactions.addLast(newTransaction);
        if(recentTransactions.size()==6) recentTransactions.pollFirst();

    }

}
