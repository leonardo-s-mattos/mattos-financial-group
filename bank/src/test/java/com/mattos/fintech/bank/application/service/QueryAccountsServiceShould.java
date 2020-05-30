package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.input.query.port.BankingAccountInfo;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.output.port.CheckingAccountQueryPort;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.SavingsAccountQueryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;
import static org.mockito.Mockito.*;

public class QueryAccountsServiceShould {

    private QueryAccountsService target;

    private CreditCardQueryPort mockCreditCardQueryPort;
    private CheckingAccountQueryPort mockCheckingAccountQueryPort;
    private SavingsAccountQueryPort mockSavingsAccountQueryPort;

    @BeforeEach
    void init(){
        mockCreditCardQueryPort = mock(CreditCardQueryPort.class);
        mockCheckingAccountQueryPort = mock(CheckingAccountQueryPort.class);
        mockSavingsAccountQueryPort = mock(SavingsAccountQueryPort.class);

        target = new QueryAccountsService(mockCreditCardQueryPort, mockCheckingAccountQueryPort, mockSavingsAccountQueryPort);
    }

    @ParameterizedTest
    @MethodSource("givenAnAccountHolderId")
    void listAllCreditCards_fromAGivenAccountHolder(String givenAccountHolderId, CreditCardAccount expectedAccount){

        when(mockCreditCardQueryPort.listAllCreditCards(givenAccountHolderId)).thenReturn(Flux.just(expectedAccount));

        Flux<CreditCardInfo> actualList = target.listAllOpenCards(givenAccountHolderId);

        StepVerifier.create(actualList)
                .expectNextMatches(created -> created.getCreditCardNumber().equals(expectedAccount.getAccountNumber()))
                .verifyComplete();


    }

    private static Stream<Arguments> givenAnAccountHolderId(){
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

        return Stream.of(Arguments.of("12345", stubAccount ));
    }



}
