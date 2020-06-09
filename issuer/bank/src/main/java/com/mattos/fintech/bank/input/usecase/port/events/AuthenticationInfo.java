package com.mattos.fintech.bank.input.usecase.port.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

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
