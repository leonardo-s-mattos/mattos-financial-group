package com.mattos.fintech.management.input.port;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mattos.fintech.management.domain.TransactionStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationInfo {

        private String creditCardNumber;
        private String originationId;
        private Integer ccvCode;
        private Integer zipCode;
        private Integer goodThroughYear;
        private Integer goodThroughMonth;
        private String status;

}
