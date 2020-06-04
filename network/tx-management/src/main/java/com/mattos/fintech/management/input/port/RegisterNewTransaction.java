package com.mattos.fintech.management.input.port;

public interface RegisterNewTransaction {

    void registerNewTransaction(PurchaseApprovalCommand command);
}
