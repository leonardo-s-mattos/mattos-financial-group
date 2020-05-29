package com.mattos.fintech.bank.domain.account;

import static org.junit.jupiter.api.Assertions.*;

import com.mattos.fintech.bank.domain.holder.AccountHolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

public class CheckingAccountShould {

    private CheckingAccount target;

    @Test
    void deposit(){

        CheckingAccount stubAccount = new CheckingAccount("Leo", new AccountHolder("12345", "PERSON"));
        assertTrue(stubAccount.getCurrentBalance().compareTo(BigDecimal.ZERO)==0);

        stubAccount.deposit(BigDecimal.valueOf(30L));

        assertTrue(stubAccount.getCurrentBalance().compareTo(BigDecimal.valueOf(30L))==0);

    }

    @Test
    void successfullWithdraw(){

        CheckingAccount stubAccount = new CheckingAccount("Leo", new AccountHolder("12345", "PERSON")).withInitialBalance(BigDecimal.valueOf(10L));

        stubAccount.withdraw(BigDecimal.valueOf(5L));

        assertTrue(stubAccount.getCurrentBalance().compareTo(BigDecimal.valueOf(5L))==0);

    }

    @Test
    void unsuccessfullWithdraw_whenNotEnoughBalance(){

        CheckingAccount stubAccount = new CheckingAccount("Leo", new AccountHolder("12345", "PERSON")).withInitialBalance(BigDecimal.valueOf(10L));

        Boolean actual = stubAccount.withdraw(BigDecimal.valueOf(20L));

        assertFalse(actual);

    }



}
