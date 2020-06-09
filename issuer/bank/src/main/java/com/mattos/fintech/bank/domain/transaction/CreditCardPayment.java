package com.mattos.fintech.bank.domain.transaction;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
public class CreditCardPayment extends Transaction {

}
