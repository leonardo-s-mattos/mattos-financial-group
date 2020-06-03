package com.mattos.fintech.authorization.output.port;

import com.mattos.fintech.authorization.domain.PurchaseInfo;

import java.util.Optional;

public interface PurchaseInfoCacheRepo {

    void pushToCache(PurchaseInfo purchaseInfo);
    Optional<PurchaseInfo> pullFromCache(String purchaseId);
}
