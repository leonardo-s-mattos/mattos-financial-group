package com.mattos.fintech.authorization.application.service;

import com.mattos.fintech.authorization.domain.PurchaseInfo;
import com.mattos.fintech.authorization.input.usecase.port.ProcessPurchaseAuthorizationInquiry;
import com.mattos.fintech.authorization.output.port.PurchaseInfoCacheRepo;
import org.springframework.stereotype.Component;

@Component
public class StashPurchaseInfoService implements ProcessPurchaseAuthorizationInquiry {

    private PurchaseInfoCacheRepo cacheRepoPort;

    public StashPurchaseInfoService(PurchaseInfoCacheRepo cacheRepoPort) {
        this.cacheRepoPort = cacheRepoPort;
    }

    @Override
    public PurchaseInfo processInquiry(PurchaseInfo purchaseInfo) {
        cacheRepoPort.pushToCache(purchaseInfo);

        return purchaseInfo;
    }
}
