package com.mattos.fintech.authorization.input.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInfo {

        private String creditCardNumber;
        private BigDecimal amount;
        private String purchaseStatus;
        private String originationId;

}
