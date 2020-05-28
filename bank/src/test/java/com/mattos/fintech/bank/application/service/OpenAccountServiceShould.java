package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.domain.account.AccountType;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OpenAccountServiceShould {

    private OpenAccountService target;

    private CreditCardAccountStatePort mockCreditCardStatePort;

    @BeforeEach
    void init(){
        mockCreditCardStatePort = mock(CreditCardAccountStatePort.class);

        target = new OpenAccountService(mockCreditCardStatePort);
    }

    @ParameterizedTest
    @MethodSource("givenAnCreditCardInfoRequest")
    void openACreditCardAccount(CreditCardRequestInfo givenRequest,
                                CreditCardAccount expectedAccount,
                                String expectedAccountNumber){

        when(mockCreditCardStatePort.create(any(CreditCardAccount.class))).thenReturn(Mono.just(expectedAccount));

        Mono<String> actualAccountNumber = target.openCreditCardAccount(givenRequest);

        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> expectedAccountNumber.equals(created))
                .verifyComplete();


    }


    private static Stream<Arguments> givenAnCreditCardInfoRequest(){
        final String taxIdNumber = "12345";
        final String stubnumber = "STUBNUMBER";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";

        AccountHolder stubAccountHolder = new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode);


        CreditCardAccount stubAccount =
                ((CreditCardAccount) AccountType.CREDIT_CARD.getInstance(stubnumber, name, stubAccountHolder)
                        .withState(OPEN))
                        .withIssuer(IssuerCompany.VISA);
        CreditCardRequestInfo stubCreditCardRequestInfo = new CreditCardRequestInfo();
        stubCreditCardRequestInfo.setAccountHolderId(taxIdNumber);
        stubCreditCardRequestInfo.setCity(city);
        stubCreditCardRequestInfo.setAccountName(name);
        stubCreditCardRequestInfo.setCountry(country);
        stubCreditCardRequestInfo.setFirstName("Leo");
        stubCreditCardRequestInfo.setIssuerCompany(IssuerCompany.VISA.name());
        stubCreditCardRequestInfo.setState(state);
        stubCreditCardRequestInfo.setStreetAddress(line1);
        stubCreditCardRequestInfo.setZipCode(zipCode);

        return Stream.of(Arguments.of(stubCreditCardRequestInfo, stubAccount, "******MBER"));
    }



}
