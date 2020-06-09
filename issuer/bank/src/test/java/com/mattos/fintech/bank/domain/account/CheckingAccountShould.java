package com.mattos.fintech.bank.domain.account;

import static com.mattos.fintech.bank.domain.account.AccountState.CLOSED;
import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static org.junit.jupiter.api.Assertions.*;
import static java.math.BigDecimal.*;
import static com.mattos.fintech.bank.util.StubFactory.*;

import com.mattos.fintech.bank.domain.transaction.DepositTransaction;
import com.mattos.fintech.bank.domain.transaction.WithdrawTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class CheckingAccountShould {

    private static CheckingAccount givenZeroedOpenCheckingAccount ;
    private static CheckingAccount givenClosedCheckingAccount;
    private static CheckingAccount given10BucksOpenCheckingAccount;

    @BeforeEach
    void setup(){

        givenZeroedOpenCheckingAccount = stubCheckingAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", ZERO, OPEN);

        givenClosedCheckingAccount = stubCheckingAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, CLOSED);

        given10BucksOpenCheckingAccount = stubCheckingAccount("12345", "WRONGNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, OPEN);

    }

    @Test
    void successfullyCredit(){

        //given
        DepositTransaction givenTransaction = stubDeposit(TEN, LocalDate.of(2020, 05, 26),
                "Check #274", givenZeroedOpenCheckingAccount);

        assertTrue(givenZeroedOpenCheckingAccount.getCurrentBalance().compareTo(ZERO)==0);

        //when
        Boolean actualResult = givenZeroedOpenCheckingAccount.credit(givenTransaction);

        //then
        assertTrue(actualResult);
        assertTrue(givenZeroedOpenCheckingAccount.getCurrentBalance().compareTo(TEN)==0);

    }

    @ParameterizedTest
    @MethodSource("givenInvalidDepositTransactionOnAccount")
    void unsuccessfullyCredit_whenInvalidAccount(CheckingAccount givenAccount, DepositTransaction givenTransaction){

        Boolean actualResult = givenAccount.credit(givenTransaction);

        assertFalse(actualResult);

    }

    @Test
    void successfullDebit(){

        //given
        WithdrawTransaction givenTransaction = stubWithdraw(TEN, LocalDate.of(2020, 05, 26),
                "from ATM", given10BucksOpenCheckingAccount);

        assertTrue(given10BucksOpenCheckingAccount.getCurrentBalance().compareTo(TEN)==0);

        Boolean actualResult = given10BucksOpenCheckingAccount.debit(givenTransaction);

        assertTrue(given10BucksOpenCheckingAccount.getCurrentBalance().compareTo(ZERO)==0);

    }

    @Test
    void unsuccessfullDebit_whenNotEnoughBalance(){

        //given
        CheckingAccount givenCheckingAccount = stubCheckingAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", ZERO, OPEN);

        WithdrawTransaction givenWithdrawTransaction =
                stubWithdraw(TEN, LocalDate.of(2020, 05, 26),
                        "Check #274", givenCheckingAccount);

        //when
        Boolean actual = givenCheckingAccount.debit(givenWithdrawTransaction);

        //then
        assertFalse(actual);
        assertTrue(givenCheckingAccount.getCurrentBalance().compareTo(ZERO)==0);

    }

    private static Stream<Arguments> givenInvalidDepositTransactionOnAccount(){

        return Stream.of(
                Arguments.of(givenClosedCheckingAccount,
                    stubDeposit(BigDecimal.TEN, LocalDate.of(2020, 05, 26),
                            "Check #274", givenClosedCheckingAccount)),
                Arguments.of(given10BucksOpenCheckingAccount,
                        stubDeposit(BigDecimal.TEN, LocalDate.of(2020, 05, 26),
                                "Check #274", givenClosedCheckingAccount)));
    }



}
