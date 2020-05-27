package com.mattos.fintech.bank.input.usecase.port;

import com.mattos.fintech.bank.input.query.port.CreditCardInfo;

public interface OpenAccount {

    public String openCreditCardAccount(CreditCardRequestInfo request);
}
