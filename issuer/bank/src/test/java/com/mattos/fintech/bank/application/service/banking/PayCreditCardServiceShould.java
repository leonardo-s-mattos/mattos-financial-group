package com.mattos.fintech.bank.application.service.banking;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.Transaction;
import com.mattos.fintech.bank.domain.transaction.TransactionType;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import com.mattos.fintech.bank.output.port.CreditCardAccountStatePort;
import com.mattos.fintech.bank.output.port.CreditCardQueryPort;
import com.mattos.fintech.bank.output.port.TransactionLogPort;
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
    private TransactionLogPort mockTransactionLogPort;

    PayCreditCardService target;

    @BeforeEach
    void init(){

        mockQueryPort = mock(CreditCardQueryPort.class);
        mockStatePort = mock(CreditCardAccountStatePort.class);
        mockTransactionLogPort = mock(TransactionLogPort.class);

        target = new PayCreditCardService(mockQueryPort, mockStatePort, mockTransactionLogPort);
    }

    @Test
    void reduceBalance_withAmountPaid(){

        //given
        TransactionRequestInfo givenTransactionInfo = TransactionRequestInfo.builder().
                transactionDate(LocalDate.of(2020, 04, 20)).
                amount(BigDecimal.TEN).targetAccountNumber("1234").
                transactionType(TransactionType.CREDIT_CARD_PAYMENT).build();


        CreditCardAccount mockAccount = mock(CreditCardAccount.class);

        when(mockQueryPort.findById("1234")).thenReturn(Mono.just(mockAccount));
        when(mockStatePort.update(mockAccount)).thenReturn(Mono.just(mockAccount));
        when(mockAccount.pay(any(CreditCardPayment.class))).thenReturn(true);
        Transaction mockTransaction = CreditCardPayment.builder().build();
        //when(mockTransactionLogPort.log(any(CreditCardPayment.class))).thenReturn(mockTransaction);


        //when
        Mono<String> actualTransactionCode = target.pay(Mono.just(givenTransactionInfo));

        //then
        assertNotNull(actualTransactionCode);
        assertTrue(!actualTransactionCode.block().isEmpty());
        verify(mockStatePort.update(mockAccount), times(1));
        when(mockAccount.pay(any(CreditCardPayment.class))).thenReturn(true);

    }

    @Test
    void handlePaymentCancellation(){

        //given
        TransactionRequestInfo givenTransactionInfo = TransactionRequestInfo.builder().
                transactionDate(LocalDate.of(2020, 04, 20)).
                amount(BigDecimal.TEN).targetAccountNumber("1234").
                transactionType(TransactionType.CREDIT_CARD_PAYMENT).build();

        CreditCardAccount mockAccount = mock(CreditCardAccount.class);

        when(mockQueryPort.findById("1234")).thenReturn(Mono.just(mockAccount));
        when(mockStatePort.update(mockAccount)).thenReturn(Mono.just(mockAccount));
        when(mockAccount.revertPay(any(CreditCardPayment.class))).thenReturn(true);
        CreditCardPayment mockTransaction = CreditCardPayment.builder().build();
        //when(mockTransactionLogPort.log(any(CreditCardPayment.class))).thenReturn(mockTransaction);

        //when
        Mono<String> actualTransactionCode = target.revert(Mono.just(givenTransactionInfo));

        //then
        assertNotNull(actualTransactionCode);
        assertTrue(!actualTransactionCode.block().isEmpty());
        verify(mockStatePort.update(mockAccount), times(1));
        verify(mockAccount.revertPay(any(CreditCardPayment.class)), times(1));

    }


}
