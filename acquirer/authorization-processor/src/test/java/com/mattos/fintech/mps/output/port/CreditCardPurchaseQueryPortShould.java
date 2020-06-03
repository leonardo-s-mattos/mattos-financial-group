package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.output.adapter.CreditCardPurchaseRepo;
import com.mattos.fintech.mps.output.adapter.mongo.CreditCardPurchaseRxRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:mongo-tests.properties")
public class CreditCardPurchaseQueryPortShould {

    @Autowired
    private CreditCardPurchaseRepo creditCardPurchaseRepo;

    @Autowired
    private CreditCardPurchaseRxRepo creditCardPurchaseRxRepo;

    @BeforeEach
    void cleanUp() {
        creditCardPurchaseRxRepo.deleteAll();
    }

    @Test
    void listAllCreditCards_fromAGivenAccountHolder() {

        Mono<CreditCardPurchase> actualAccountNumber = givenOneCreditCardPurchase();

        Flux<CreditCardPurchase> actualList = creditCardPurchaseRepo.listAll();

        Assertions.assertNotNull(actualList);
        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> created.getCreditCardNumber().equals("1234"))
                .verifyComplete();

    }

    private Mono<CreditCardPurchase> givenOneCreditCardPurchase() {
        CreditCardPurchase stubCreditCardPurchase = CreditCardPurchase.builder()
                .creditCardNumber("1234")
                .amount(BigDecimal.TEN)
                .id(UUID.randomUUID())
                .merchantId("123456")
                .time(LocalDateTime.now()).build();

        Mono<CreditCardPurchase> actualAccountNumber = creditCardPurchaseRepo.save(stubCreditCardPurchase);

        StepVerifier.create(actualAccountNumber)
                .expectNextMatches(created -> !created.getCreditCardNumber().isEmpty())
                .verifyComplete();
        return actualAccountNumber;
    }



}
