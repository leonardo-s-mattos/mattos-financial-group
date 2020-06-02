package com.mattos.fintech.bank.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
public class CreditCardPaymentCancelled extends Transaction {

}
