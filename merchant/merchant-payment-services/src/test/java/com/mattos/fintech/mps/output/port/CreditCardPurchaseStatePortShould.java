package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.output.adapter.CreditCardPurchaseRepo;
import com.mattos.fintech.mps.output.adapter.mongo.CreditCardPurchaseRxRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditCardPurchaseStatePortShould {

    private CreditCardPurchaseStatePort target;
    private CreditCardPurchaseRxRepo mockMongoRepo;

    @BeforeEach
    void setup(){
        mockMongoRepo = mock(CreditCardPurchaseRxRepo.class);
        target = new CreditCardPurchaseRepo(mockMongoRepo);

    }

    @ParameterizedTest
    @MethodSource("givenAPurchase")
    void createCreditCardPurchase(CreditCardPurchase givenAccount){

        when(mockMongoRepo.save(givenAccount)).thenReturn(Mono.just(givenAccount));

        Mono<CreditCardPurchase> actual = target.save(givenAccount);

        assertNotNull(actual);

    }

    @ParameterizedTest
    @MethodSource("givenAPurchase")
    void updateCreditCardPurchaseState(CreditCardPurchase givenPurchase){

        when(mockMongoRepo.save(givenPurchase)).thenReturn(Mono.just(givenPurchase));

        Mono<CreditCardPurchase> actual = target.save(givenPurchase);

        assertNotNull(actual);

    }

    private static Stream<Arguments> givenAPurchase(){

        CreditCardPurchase stubCreditCardPurchase = CreditCardPurchase.builder()
                .creditCardNumber("1234")
                .amount(BigDecimal.TEN)
                .id(UUID.randomUUID())
                .merchantId("123456")
                .time(LocalDateTime.now()).build();

        return Stream.of(Arguments.of(stubCreditCardPurchase));
    }
}
