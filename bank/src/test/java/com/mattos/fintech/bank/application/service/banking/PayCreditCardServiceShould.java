package com.mattos.fintech.bank.application.service.banking;

import com.mattos.fintech.bank.application.service.events.TransactionEvents;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PayCreditCardServiceShould {

    CreditCardQueryPort mockQueryPort;
    CreditCardAccountStatePort mockStatePort;

    TransactionEvents.NewTransactionEvent mockProcessor;

    PayCreditCardService target;

    @BeforeEach
    void init(){

        mockQueryPort = mock(CreditCardQueryPort.class);
        mockStatePort = mock(CreditCardAccountStatePort.class);

        mockProcessor = mock(TransactionEvents.NewTransactionEvent.class);

        target = new PayCreditCardService(mockQueryPort, mockStatePort);
    }

    @Test
    void reduceBalance_withAmountPaid(){

        //given
        CreditCardPayment givenTransactionInfo = CreditCardPayment.builder().
                transactionDate(LocalDate.of(2020, 04, 20)).
                amount(BigDecimal.TEN).targetAccount("1234").build();

        CreditCardAccount mockAccount = mock(CreditCardAccount.class);

        when(mockQueryPort.findById("1234")).thenReturn(Mono.just(mockAccount));
        when(mockStatePort.update(mockAccount)).thenReturn(Mono.just(mockAccount));
        when(mockAccount.pay(any(CreditCardPayment.class))).thenReturn(true);

        //when
        Flux<String> actualTransactionCode = target.pay(Flux.just(givenTransactionInfo));

        //then
        assertNotNull(actualTransactionCode);
        assertTrue(!actualTransactionCode.blockFirst().isEmpty());
        verify(mockStatePort.update(mockAccount), times(1));
        when(mockAccount.pay(any(CreditCardPayment.class))).thenReturn(true);

    }

    @Test
    void handlePaymentCancellation(){

        //given
        CreditCardPayment givenTransactionInfo = CreditCardPayment.builder().
                transactionDate(LocalDate.of(2020, 04, 20)).
                amount(BigDecimal.TEN).targetAccount("1234").build();

        CreditCardAccount mockAccount = mock(CreditCardAccount.class);

        when(mockQueryPort.findById("1234")).thenReturn(Mono.just(mockAccount));
        when(mockStatePort.update(mockAccount)).thenReturn(Mono.just(mockAccount));
        when(mockAccount.revertPay(any(CreditCardPayment.class))).thenReturn(true);

        //when
        Flux<String> actualTransactionCode = target.revert(Flux.just(givenTransactionInfo));

        //then
        assertNotNull(actualTransactionCode);
        assertTrue(!actualTransactionCode.blockFirst().isEmpty());
        verify(mockStatePort.update(mockAccount), times(1));
        verify(mockAccount.revertPay(any(CreditCardPayment.class)), times(1));

    }


}
