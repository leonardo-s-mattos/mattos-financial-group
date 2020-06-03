package com.mattos.fintech.authorization.input.usecase.adapter;

import com.mattos.fintech.authorization.application.service.StashPurchaseInfoService;
import com.mattos.fintech.authorization.domain.PurchaseInfo;
import com.mattos.fintech.authorization.queues.Authorization;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class PurchaseAuthorizationQueueListener  {

    private StashPurchaseInfoService stashPurchaseInfoService;

    public PurchaseAuthorizationQueueListener(StashPurchaseInfoService stashPurchaseInfoService) {
        this.stashPurchaseInfoService = stashPurchaseInfoService;
    }

    @Transformer(inputChannel = Authorization.AUTHORIZATION_INQUIRY,
            outputChannel = Authorization.AUTHORIZATION_REQUEST)
    public PurchaseInfo listen(PurchaseInfo purchaseInfo) {
        return stashPurchaseInfoService.processInquiry(purchaseInfo);
    }
}
