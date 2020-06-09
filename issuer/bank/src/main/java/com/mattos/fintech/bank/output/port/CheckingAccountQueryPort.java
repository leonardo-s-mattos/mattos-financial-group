package com.mattos.fintech.bank.output.port;

import com.mattos.fintech.bank.domain.account.CheckingAccount;
import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import reactor.core.publisher.Flux;


public interface CheckingAccountQueryPort {

    Flux<CheckingAccount> listAllAccounts(String accountHolderId);
}
