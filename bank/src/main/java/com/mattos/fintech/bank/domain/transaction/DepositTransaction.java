package com.mattos.fintech.bank.domain.transaction;

import com.mattos.fintech.bank.domain.account.Account;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode
@ToString
public class DepositTransaction extends Transaction {

    private Account targetAccount;
    private String comment;
}
