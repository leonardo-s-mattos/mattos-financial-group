package com.mattos.fintech.bank.output.port;


import com.mattos.fintech.bank.domain.account.AccountType;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.output.adapter.CreditCardAccountRepository;
import com.mattos.fintech.bank.output.adapter.mongo.CreditCardAccountReactiveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static com.mattos.fintech.bank.domain.account.AccountState.OPEN;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:mongo-tests.properties")
public class CreditCardQueryPortShould {

    @Autowired
    private CreditCardAccountRepository creditCardAccountRepository;

    @Autowired
    private CreditCardAccountReactiveRepository creditCardAccountReactiveRepository;

    @BeforeEach
    void cleanUp() {
        creditCardAccountReactiveRepository.deleteAll();
    }

    @Test
    void listAllCreditCards_fromAGivenAccountHolder() {

        Mono<CreditCardAccount> actualAccountNumber = givenOneCreditCardAccount();

        Flux<CreditCardAccount> actualList = creditCardAccountRepository.listAllCreditCards("12345");


        Assertions.assertNotNull(actualList);
        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> created.getAccountHolder().getTaxIdNumber().equals("12345"))
                .verifyComplete();

    }

    private Mono<CreditCardAccount> givenOneCreditCardAccount() {
        CreditCardAccount stubCreditCardAccount = givenAnAccountHolderId("12345");

        Mono<CreditCardAccount> actualAccountNumber = creditCardAccountRepository.create(stubCreditCardAccount);

        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> !created.getAccountNumber().isEmpty())
                .verifyComplete();
        return actualAccountNumber;
    }

    @Test
    void retrieveAccountById(){
        // given
        final String givenAccountNumber = givenOneCreditCardAccount().block().getAccountNumber();

        //when
        Mono<CreditCardAccount> actualAccount = creditCardAccountRepository.findById(givenAccountNumber);

        //then
        Assertions.assertNotNull(actualAccount);
        StepVerifier.create(actualAccount)
                .expectNextMatches(created -> created.getAccountNumber().equals(givenAccountNumber))
                .verifyComplete();

    }

    private static CreditCardAccount givenAnAccountHolderId(final String taxIdNumber) {
        final String line1 = "111 Sesame St";
        final String city = "Sesame Place";
        final String state = "SE";
        final String country = "US";
        final String zipCode = "12121";
        final String name = "stubAccount";

        AccountHolder stubAccountHolder = new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode);


        CreditCardAccount stubAccount =
                ((CreditCardAccount) AccountType.CREDIT_CARD.getInstance(name, stubAccountHolder)
                        .withState(OPEN))
                        .withIssuer(IssuerCompany.VISA);


        return stubAccount;
    }
}
