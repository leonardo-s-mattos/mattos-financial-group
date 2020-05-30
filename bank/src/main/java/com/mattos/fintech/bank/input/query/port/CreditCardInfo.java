package com.mattos.fintech.bank.input.query.port;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInfo {

    private String creditCardNumber;
    private String creditCardLastDigits;
    private Integer goodThoughMonth;
    private Integer goodThoughYear;


}
