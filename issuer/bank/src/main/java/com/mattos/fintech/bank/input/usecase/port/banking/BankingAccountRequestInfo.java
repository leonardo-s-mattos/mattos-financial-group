package com.mattos.fintech.bank.input.usecase.port.banking;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BankingAccountRequestInfo {

    String accountName;
    String accountHolderId;
    String firstName;
    String lastName;
    String taxIdNumber;


}
