package com.mattos.fintech.bank.input.query.port;

import com.mattos.fintech.bank.domain.transaction.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardInfo {

    private String creditCardNumber;
    private String creditCardLastDigits;
    private Integer goodThoughMonth;
    private Integer goodThoughYear;
    private Integer ccvCode;
    private BigDecimal currentBalance;
    private BigDecimal limitAmount;
    private List<Transaction> latesTransactions;


}
