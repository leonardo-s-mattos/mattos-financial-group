package com.mattos.fintech.mps.output.adapter.rabbit;


import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.input.usecase.port.PurchaseInfo;
import com.mattos.fintech.mps.output.port.ApprovePurchasePort;
import com.mattos.fintech.mps.queues.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnector implements ApprovePurchasePort{

    @Bean
    @InboundChannelAdapter(value = Authorization.AUTHORIZATION_INQUIRY)
    MessageSource<PurchaseInfo> sendPurchaseInfo(PurchaseInfo purchaseInfo){
        return () -> MessageBuilder.withPayload(purchaseInfo).build();
    }

    @Bean
    @InboundChannelAdapter(value = Authorization.CONFIRMED_PURCHASE)
    MessageSource<CreditCardPurchase> sendConfirmedPurchase(CreditCardPurchase purchaseInfo){
        return () -> MessageBuilder.withPayload(purchaseInfo).build();
    }

    @Override
    public void requestPurchaseAuthorization(PurchaseInfo purchase) {
        sendPurchaseInfo(purchase);
    }

    @Override
    public void informConfirmedPurchase(CreditCardPurchase purchase) {
        sendConfirmedPurchase(purchase);
    }
}
