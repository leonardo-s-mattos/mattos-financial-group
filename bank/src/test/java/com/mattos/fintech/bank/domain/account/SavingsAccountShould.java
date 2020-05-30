package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;
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

import static com.mattos.fintech.bank.domain.account.AccountState.CLOSED;
import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.mattos.fintech.bank.util.StubFactory.*;

public class SavingsAccountShould {

    private static SavingsAccount givenZeroedOpenSavingsAccount ;
    private static SavingsAccount givenClosedSavingsAccount;
    private static SavingsAccount given10BucksOpenSavingsAccount;

    @BeforeEach
    void setup(){

        givenZeroedOpenSavingsAccount = stubSavingsAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", ZERO, OPEN);

        givenClosedSavingsAccount = stubSavingsAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, CLOSED);

        given10BucksOpenSavingsAccount = stubSavingsAccount("12345", "WRONGNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, OPEN);

    }

    @Test
    void successfullyCredit(){

        //given
        DepositTransaction givenTransaction = stubDeposit(TEN, LocalDate.of(2020, 05, 26),
                "Check #274", givenZeroedOpenSavingsAccount);

        assertTrue(givenZeroedOpenSavingsAccount.getCurrentBalance().compareTo(ZERO)==0);

        //when
        Boolean actualResult = givenZeroedOpenSavingsAccount.credit(givenTransaction);

        //then
        assertTrue(actualResult);
        assertTrue(givenZeroedOpenSavingsAccount.getCurrentBalance().compareTo(TEN)==0);

    }

    @ParameterizedTest
    @MethodSource("givenInvalidDepositTransactionOnAccount")
    void unsuccessfullyCredit_whenInvalidAccount(SavingsAccount givenAccount, DepositTransaction givenTransaction){

        Boolean actualResult = givenAccount.credit(givenTransaction);

        assertFalse(actualResult);

    }

    @Test
    void successfullyDebit(){

        //given
        WithdrawTransaction givenTransaction = stubWithdraw(TEN, LocalDate.of(2020, 05, 26),
                "from ATM", given10BucksOpenSavingsAccount);

        assertTrue(given10BucksOpenSavingsAccount.getCurrentBalance().compareTo(TEN)==0);

        Boolean actualResult = given10BucksOpenSavingsAccount.debit(givenTransaction);

        assertTrue(given10BucksOpenSavingsAccount.getCurrentBalance().compareTo(ZERO)==0);

    }

    @Test
    void unsuccessfullyDebit_whenNotEnoughBalance(){

        //given
        SavingsAccount givenSavingsAccount = stubSavingsAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", ZERO, OPEN);

        WithdrawTransaction givenWithdrawTransaction =
                stubWithdraw(TEN, LocalDate.of(2020, 05, 26),
                        "Check #274", givenSavingsAccount);

        //when
        Boolean actual = givenSavingsAccount.debit(givenWithdrawTransaction);

        //then
        assertFalse(actual);
        assertTrue(givenSavingsAccount.getCurrentBalance().compareTo(ZERO)==0);

    }

    private static Stream<Arguments> givenInvalidDepositTransactionOnAccount(){

        return Stream.of(
                Arguments.of(givenClosedSavingsAccount,
                    stubDeposit(BigDecimal.TEN, LocalDate.of(2020, 05, 26),
                            "Check #274", givenClosedSavingsAccount)),
                Arguments.of(given10BucksOpenSavingsAccount,
                        stubDeposit(BigDecimal.TEN, LocalDate.of(2020, 05, 26),
                                "Check #274", givenClosedSavingsAccount)));
    }



}
