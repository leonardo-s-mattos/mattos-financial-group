package com.mattos.fintech.mps.input.usecase.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.mps.domain.IssuerCompany;
import com.mattos.fintech.mps.domain.PurchaseStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseInfo {

        private String creditCardNumber;
        private Integer ccvCode;
        private Integer zipCode;
        private Integer goodThroughYear;
        private Integer goodThroughMonth;
        private BigDecimal amount;
        private PurchaseStatus purchaseStatus;
        private IssuerCompany issuerCompany;
        private LocalDateTime time;
        private String merchantId;

}
