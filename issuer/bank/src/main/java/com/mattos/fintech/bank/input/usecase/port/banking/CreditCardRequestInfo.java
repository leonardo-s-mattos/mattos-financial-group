package com.mattos.fintech.bank.input.usecase.port.banking;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreditCardRequestInfo {

    String issuerCompany;
    String accountName;
    String accountHolderId;
    String firstName;
    String lastName;
    String streetAddress;
    String city;
    String state;
    String country;
    String zipCode;


}
