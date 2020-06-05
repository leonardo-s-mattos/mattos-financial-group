package com.mattos.fintech.management.output.port;

import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;

import java.util.Optional;

public interface TemporaryCacheRepo {

    void pushToCache(PurchaseApprovalCommand purchaseInfo);
    Optional<PurchaseApprovalCommand> pullFromCache(String originationId);
}
