package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardAccountShould {

    @ParameterizedTest
    @MethodSource("givenAnPaymentTransactionOnAccount")
    void decreaseBalance_whenReceivePayment(CreditCardAccount givenAccount, CreditCardPayment stubPayment){

        Boolean actualResult = givenAccount.receivePayment(stubPayment);
        assertTrue(actualResult);
        assertEquals(givenAccount.getCurrentBalance().longValue(), 0L);
        assertEquals(givenAccount.getLastestTransactions().get(4), stubPayment);

    }

    @Test
    void increaseBalance_whenNewPurchaseAuthorized(){

        Boolean actualResult = givenAccount.purchase(stubPayment);
        assertTrue(actualResult);
        assertEquals(givenAccount.getCurrentBalance().longValue(), 20L);
        assertEquals(givenAccount.getLastestTransactions().get(4), stubPayment);

    }

    @Test
    void decreaseBalance_whenPurchaseIsCancelled(){

    }

    private static Stream<Arguments> givenAnPaymentTransactionOnAccount(){
        final String taxIdNumber = "12345";
        final String stubnumber = "STUBNUMBER";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";

        return Stream.of(Arguments.of(
                stubCreditCardAccount(taxIdNumber, stubnumber, line1, city, state, country, zipCode, name, BigDecimal.TEN)
                , stubCreditCardPayment(BigDecimal.TEN, LocalDate.of(2020, 05, 26), "from checking")));
    }

    private static CreditCardAccount stubCreditCardAccount(String taxIdNumber, String stubnumber, String line1, String city, String state, String country, String zipCode, String name, BigDecimal balance) {
        return ((CreditCardAccount) AccountType.CREDIT_CARD.getInstance(stubnumber, name, new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode))
                .withState(OPEN))
                .withIssuer(IssuerCompany.VISA)
                .withBalance(balance);
    }

    private static CreditCardPayment stubCreditCardPayment(BigDecimal amount, LocalDate paymentDate, String comment) {
        return new CreditCardPayment().withAmount(amount).withPaymentDate(paymentDate).withComment(comment);
    }

    private static Stream<Arguments> givenAnPurchaseTransactionOnAccount(){
        final String taxIdNumber = "12345";
        final String stubnumber = "STUBNUMBER";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";

        return Stream.of(Arguments.of(
                stubCreditCardAccount(taxIdNumber, stubnumber, line1, city, state, country, zipCode, name, BigDecimal.TEN)
                , stubCreditCardPayment(BigDecimal.TEN, LocalDate.of(2020, 05, 26), "from checking")));
    }

    private static CreditCardAccount stubCreditCardAccount(String taxIdNumber, String stubnumber, String line1, String city, String state, String country, String zipCode, String name, BigDecimal balance) {
        return ((CreditCardAccount) AccountType.CREDIT_CARD.getInstance(stubnumber, name, new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode))
                .withState(OPEN))
                .withIssuer(IssuerCompany.VISA)
                .withBalance(balance);
    }

    private static CreditCardPurchase stubCreditCardPurchase(BigDecimal amount, LocalDate purchaseDate, String comment) {
        return new CreditCardPurchase().withAmount(amount).withTransactionDate(purchaseDate).withComment(comment);
    }

}
