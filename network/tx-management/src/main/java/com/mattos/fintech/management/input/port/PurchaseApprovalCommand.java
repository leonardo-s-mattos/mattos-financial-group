package com.mattos.fintech.management.input.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.management.domain.TransactionStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedisHash("purchaseApproval")
public class PurchaseApprovalCommand {

        @Id
        private String purchaseId;
        private String creditCardNumber;
        private Integer ccvCode;
        private Integer zipCode;
        private Integer goodThroughYear;
        private Integer goodThroughMonth;
        private BigDecimal amount;
        private TransactionStatus purchaseStatus;
        private LocalDateTime time;
        private String merchantId;
        private String issuerBank;
        private String acquirerBank;

        public void withPurchaseId(String purchaseId){
                this.purchaseId = purchaseId;
        }

}
