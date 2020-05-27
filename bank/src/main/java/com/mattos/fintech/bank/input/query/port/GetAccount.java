package com.mattos.fintech.bank.input.query.port;

import java.util.List;

public interface GetAccount {

    public List<CreditCardInfo> listAllOpenCards(String accountHolderId);

}
