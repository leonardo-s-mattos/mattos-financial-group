package com.mattos.fintech.bank.domain.account;


import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountShould {

    @ParameterizedTest
    @MethodSource("creditCardAccountsToOpen")
    public void generateNewAccounts_withCorrectAccountNumbers(IssuerCompany issuerCompany, String accountName, AccountHolder accountHolder){
        CreditCardAccount actual = (CreditCardAccount)AccountType.CREDIT_CARD.getInstance(accountName, accountHolder);
        actual.withIssuer(issuerCompany);

        assertNotNull(actual.getAccountNumber());
        assertThat(actual.getAccountNumber().length()).isEqualTo(16);
        assertNotNull(actual.getLastFourDigits());
        assertThat(actual.getLastFourDigits().length()).isEqualTo(16);

    }

    private static Stream<Arguments> creditCardAccountsToOpen(){
        return Stream.of(Arguments.of(IssuerCompany.VISA, "MyFirstVisa", new Person("Leo", "Mattos", "12345")));
    }
}
