package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.Account;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AccountQuery {

    Flux<Account> listAllCreditCards(String accountHolderId);
}
