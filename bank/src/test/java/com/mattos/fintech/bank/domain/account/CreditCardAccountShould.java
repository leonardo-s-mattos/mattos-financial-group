package com.mattos.fintech.bank.domain.account;


import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.CreditCardPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.mattos.fintech.bank.domain.account.AccountState.CLOSED;
import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.*;
import static com.mattos.fintech.bank.util.StubFactory.*;

public class CreditCardAccountShould {

    private static CreditCardAccount givenZeroedOpenCreditCardAccount ;
    private static CreditCardAccount givenClosedCreditCardAccount;
    private static CreditCardAccount given10BucksOpenCreditCardAccount;

    @BeforeEach
    void setup(){

        givenZeroedOpenCreditCardAccount = stubCreditCardAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", ZERO, OPEN);

        givenClosedCreditCardAccount = stubCreditCardAccount("12345", "STUBNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, CLOSED);

        given10BucksOpenCreditCardAccount = stubCreditCardAccount("12345", "WRONGNUMBER",
                "111 Sesame St", "Sesame Place", "SE", "US",  "12121",
                "stubAccount", BigDecimal.TEN, OPEN);

    }

    @Test
    void decreaseBalance_whenReceivePayment(){

        //given
        CreditCardPayment givenPaymentTransaction = stubCreditCardPayment(BigDecimal.valueOf(5L), LocalDate.of(2020, 5, 28), "Balance payment");

        //when
        Boolean actualResult = given10BucksOpenCreditCardAccount.pay(givenPaymentTransaction);

        //then
        assertTrue(actualResult);
        assertEquals(given10BucksOpenCreditCardAccount.getCurrentBalance().longValue(), 5L);
        assertEquals(given10BucksOpenCreditCardAccount.getRecentTransactions().getLast(), givenPaymentTransaction);

    }

    @Test
    void increaseBalance_whenNewPurchaseAuthorized(){

        //given
        CreditCardPurchase givenPurchaseTransaction = stubCreditCardPurchase(BigDecimal.valueOf(5L), LocalDate.of(2020, 5, 28), "Balance payment");

        Boolean actualResult = givenZeroedOpenCreditCardAccount.purchase(givenPurchaseTransaction);
        assertTrue(actualResult);
        assertEquals(givenZeroedOpenCreditCardAccount.getCurrentBalance().longValue(), 5L);
        assertEquals(givenZeroedOpenCreditCardAccount.getRecentTransactions().getLast(), givenPurchaseTransaction);

    }

    @Test
    void decreaseBalance_whenPurchaseIsCancelled(){

        //given
        CreditCardPurchase givenPurchaseTransaction = stubCreditCardPurchase(BigDecimal.valueOf(5L), LocalDate.of(2020, 5, 28), "Balance payment");

        //when
        Boolean actualResult = given10BucksOpenCreditCardAccount.revertPurchase(givenPurchaseTransaction);

        //then
        assertTrue(actualResult);
        assertEquals(given10BucksOpenCreditCardAccount.getCurrentBalance().longValue(), 5L);
        assertEquals(given10BucksOpenCreditCardAccount.getRecentTransactions().getLast(), givenPurchaseTransaction);

    }



}
