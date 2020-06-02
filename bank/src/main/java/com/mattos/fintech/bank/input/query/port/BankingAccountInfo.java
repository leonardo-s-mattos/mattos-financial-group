package com.mattos.fintech.bank.input.query.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.bank.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankingAccountInfo {

    private String accountNumber;
    private String lastDigits;
    private BigDecimal balance;
    private String accountType;


}
