package com.mattos.fintech.mps.application.service;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.domain.PurchaseStatus;
import com.mattos.fintech.mps.input.usecase.port.PayWithCard;
import com.mattos.fintech.mps.input.usecase.port.PurchaseInfo;
import com.mattos.fintech.mps.output.port.ApprovePurchasePort;
import com.mattos.fintech.mps.output.port.CreditCardPurchaseStatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import static reactor.function.TupleUtils.function;

import java.time.LocalDateTime;

@Component
public class PayWithCardUseCase implements PayWithCard {

    private CreditCardPurchaseStatePort creditCardPurchaseStatePort;
    private ApprovePurchasePort approvePurchasePort;

    @Autowired
    public PayWithCardUseCase(CreditCardPurchaseStatePort creditCardPurchaseStatePort, ApprovePurchasePort approvePurchasePort) {
        this.creditCardPurchaseStatePort = creditCardPurchaseStatePort;
        this.approvePurchasePort = approvePurchasePort;
    }

    @Override
    public Mono<String> requestAuthorization(Mono<PurchaseInfo> cardInfo) {
        return cardInfo.zipWith(cardInfo.map( purchaseInfo -> CreditCardPurchase.builder()
                .amount(purchaseInfo.getAmount())
                .creditCardNumber(purchaseInfo.getCreditCardNumber())
                .purchaseStatus(PurchaseStatus.PENDING)
                .time(LocalDateTime.now())
                .merchantId("19191919").build()))

                .map(function ((purchaseInfo, tx) ->  {

                   creditCardPurchaseStatePort.save(tx.withId()).subscribe();
                   purchaseInfo.withPurchaseId(tx.getId().toString());
                   approvePurchasePort.requestPurchaseAuthorization(purchaseInfo);

                   return tx.getId().toString();
                }))
                ;
    }

    @Override
    public Mono<String> confirmPurchase(Mono<PurchaseInfo> purchaseInfo) {
        return null;
    }
}
