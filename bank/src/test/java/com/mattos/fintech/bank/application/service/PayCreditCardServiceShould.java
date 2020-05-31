package com.mattos.fintech.bank.application.service;

import com.mattos.fintech.bank.input.usecase.port.TransactionRequestInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayCreditCardServiceShould {

    PayCreditCardService target;

    @BeforeEach
    void init(){

        //target = new PayCreditCardService();
    }

    @Test
    void reduceBalance_withAmountPaid(){

        TransactionRequestInfo givenTransactionInfo = TransactionRequestInfo.builder().
                transactionDate(LocalDate.of(2020, 04, 20)).
                amount(BigDecimal.TEN).targetAccountNumber("1234").build();
        target.pay(Mono.just(givenTransactionInfo));

    }
}
