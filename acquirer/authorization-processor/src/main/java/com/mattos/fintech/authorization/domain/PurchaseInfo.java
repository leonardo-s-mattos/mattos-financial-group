package com.mattos.fintech.authorization.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RedisHash("purchaseInfo")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class PurchaseInfo {

        @Id
        private String purchaseId;
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

        public void withPurchaseId(String purchaseId){
                this.purchaseId = purchaseId;
        }

}
