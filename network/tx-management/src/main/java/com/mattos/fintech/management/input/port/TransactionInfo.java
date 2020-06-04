package com.mattos.fintech.management.input.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.management.domain.TransactionStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInfo {

        private String creditCardNumber;
        private BigDecimal amount;
        private TransactionStatus purchaseStatus;

}
