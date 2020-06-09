package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.transaction.DepositTransaction;
import com.mattos.fintech.bank.domain.transaction.WithdrawTransaction;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Random;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
@Document
public class CheckingAccount extends Account{

    private BigDecimal currentBalance;

    private static EnumSet<AccountState> inactiveStates =
            EnumSet.of(AccountState.INACTIVE, AccountState.PENDING, AccountState.CLOSED);

    public Boolean credit(DepositTransaction deposit){
        if(inactiveStates.contains(getState())) return false;
        if(!deposit.getTargetAccount().equals(this)) return false;
        currentBalance = currentBalance.add(deposit.getAmount());
        addTransaction(deposit);
        return true;
    }

    public Boolean debit(WithdrawTransaction withdraw){
        if(inactiveStates.contains(getState())) return false;
        if(!withdraw.getTargetAccount().equals(this)) return false;
        if(currentBalance.compareTo(withdraw.getAmount()) < 0) return false;
        currentBalance = currentBalance.subtract(withdraw.getAmount());
        addTransaction(withdraw);
        return true;
    }

    public CheckingAccount withBalance(BigDecimal balance){
        this.currentBalance = balance;
        return this;
    }

    public CheckingAccount withAccountNumber(){
        String random = String.valueOf(new Random().nextLong());
        super.withNumber(random.substring(random.length()-10));
        return this;
    }

}
