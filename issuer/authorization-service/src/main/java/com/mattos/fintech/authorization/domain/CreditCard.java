package com.mattos.fintech.authorization.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCard {

    private String creditCardNumber;
    private String creditCardLastDigits;
    private Integer goodThoughMonth;
    private Integer goodThoughYear;
    private Integer ccvCode;
    private BigDecimal currentBalance;
    private BigDecimal limitAmount;


}
