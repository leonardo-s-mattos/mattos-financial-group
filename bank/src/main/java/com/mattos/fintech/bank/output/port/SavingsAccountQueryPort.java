package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.domain.account.SavingsAccount;
import reactor.core.publisher.Flux;


public interface SavingsAccountQueryPort {

    Flux<SavingsAccount> listAllAccounts(String accountHolderId);
}
