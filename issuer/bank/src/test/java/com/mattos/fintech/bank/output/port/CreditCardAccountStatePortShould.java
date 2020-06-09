package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.AccountType;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.IssuerCompany;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.output.adapter.CreditCardAccountRepository;
import com.mattos.fintech.bank.output.adapter.mongo.CreditCardAccountReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;

import static com.mattos.fintech.bank.domain.account.AccountState.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CreditCardAccountStatePortShould {

    private CreditCardAccountStatePort target;
    private CreditCardAccountReactiveRepository mockMongoRepo;

    @BeforeEach
    void setup(){
        mockMongoRepo = mock(CreditCardAccountReactiveRepository.class);
        target = new CreditCardAccountRepository(mockMongoRepo);

    }

    @ParameterizedTest
    @MethodSource("givenAnAccount")
    void createCreditCardAccount(CreditCardAccount givenAccount){

        when(mockMongoRepo.save(givenAccount)).thenReturn(Mono.just(givenAccount));

        Mono<CreditCardAccount> actual = target.create(givenAccount);

        assertNotNull(actual);

    }

    @ParameterizedTest
    @MethodSource("givenAnAccount")
    void updateCreditCardAccountState(CreditCardAccount givenAccount){

        when(mockMongoRepo.save(givenAccount)).thenReturn(Mono.just(givenAccount));

        Mono<CreditCardAccount> actual = target.update(givenAccount);

        assertNotNull(actual);

    }

    private static Stream<Arguments> givenAnAccount(){
        AccountHolder stubAccountHolder = new AccountHolder("12345", "PERSON")
                .withBillingAddress("111 Sesame St", "Sesame Place", "SE", "US", "12121");
        CreditCardAccount stubAccount =
                ((CreditCardAccount)AccountType.CREDIT_CARD.getInstance("STUBNUMBER", "stubAccount", stubAccountHolder)
                        .withState(OPEN))
                        .withIssuer(IssuerCompany.VISA);

        return Stream.of(Arguments.of(stubAccount));
    }
}
