package com.mattos.fintech.mps.output.port;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import com.mattos.fintech.mps.input.usecase.port.PurchaseInfo;

public interface ApprovePurchasePort {

    void requestPurchaseAuthorization(PurchaseInfo purchase);
    void informConfirmedPurchase(CreditCardPurchase purchase);
}
