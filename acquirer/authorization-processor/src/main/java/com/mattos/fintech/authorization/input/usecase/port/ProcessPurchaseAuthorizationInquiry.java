package com.mattos.fintech.authorization.input.usecase.port;

import com.mattos.fintech.authorization.domain.PurchaseInfo;
import reactor.core.publisher.Mono;

public interface ProcessPurchaseAuthorizationInquiry {

    PurchaseInfo processInquiry(PurchaseInfo purchaseInfo);
}
