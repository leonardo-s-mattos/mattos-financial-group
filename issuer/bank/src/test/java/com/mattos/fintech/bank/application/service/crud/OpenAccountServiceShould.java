package com.mattos.fintech.bank.application.service.crud;

import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.input.usecase.port.banking.BankingAccountRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.CreditCardRequestInfo;
import com.mattos.fintech.bank.output.port.CheckingAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.SavingsAccountStatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static com.mattos.fintech.bank.util.StubFactory.*;
import static java.math.BigDecimal.ZERO;
import static org.mockito.Mockito.*;

public class OpenAccountServiceShould {

    private OpenAccountService target;

    private CreditCardAccountStatePort mockCreditCardStatePort;
    private CheckingAccountStatePort mockCheckingStatePort;
    private SavingsAccountStatePort mockSavingsStatePort;

    @BeforeEach
    void init(){
        mockCreditCardStatePort = mock(CreditCardAccountStatePort.class);
        mockCheckingStatePort = mock(CheckingAccountStatePort.class);
        mockSavingsStatePort = mock(SavingsAccountStatePort.class);

        target = new OpenAccountService(mockCreditCardStatePort, mockCheckingStatePort, mockSavingsStatePort );
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

    @ParameterizedTest
    @MethodSource("givenAnCheckingInfoRequest")
    void openACheckingAccount(BankingAccountRequestInfo givenRequest,
                              CheckingAccount expectedAccount,
                              String expectedAccountNumber){

        when(mockCheckingStatePort.create(any(CheckingAccount.class))).thenReturn(Mono.just(expectedAccount));

        Mono<String> actualAccountNumber = target.openCheckingAccount(givenRequest);

        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> expectedAccountNumber.equals(created))
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("givenAnSavingsAccountInfoRequest")
    void openASavingsAccount(BankingAccountRequestInfo givenRequest,
                                SavingsAccount expectedAccount,
                                String expectedAccountNumber){

        when(mockSavingsStatePort.create(any(SavingsAccount.class))).thenReturn(Mono.just(expectedAccount));

        Mono<String> actualAccountNumber = target.openSavingsAccount(givenRequest);

        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> expectedAccountNumber.equals(created))
                .verifyComplete();
    }

    private static Stream<Arguments> givenAnCreditCardInfoRequest(){
        final String taxIdNumber = "12345";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";


        CreditCardAccount stubAccount = stubCreditCardAccount(taxIdNumber, "STUBNUMBER",
                line1, city, state, country,  zipCode,
                name, ZERO, OPEN);


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

    private static Stream<Arguments> givenAnCheckingInfoRequest(){
        final String taxIdNumber = "12345";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";


        CheckingAccount stubAccount = stubCheckingAccount(taxIdNumber, "STUBNUMBER",
                line1, city, state, country,  zipCode,
                name, ZERO, OPEN);


        BankingAccountRequestInfo stubCreditCardRequestInfo = BankingAccountRequestInfo.builder().accountHolderId(taxIdNumber)
                .accountName(name).taxIdNumber(taxIdNumber).build();

        return Stream.of(Arguments.of(stubCreditCardRequestInfo, stubAccount, "******MBER"));
    }

    private static Stream<Arguments> givenAnSavingsAccountInfoRequest(){
        final String taxIdNumber = "12345";
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final  String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";


        SavingsAccount stubAccount = stubSavingsAccount(taxIdNumber, "STUBNUMBER",
                line1, city, state, country,  zipCode,
                name, ZERO, OPEN);


        BankingAccountRequestInfo stubCreditCardRequestInfo = BankingAccountRequestInfo.builder().accountHolderId(taxIdNumber)
                .accountName(name).taxIdNumber(taxIdNumber).build();

        return Stream.of(Arguments.of(stubCreditCardRequestInfo, stubAccount, "******MBER"));
    }



}
