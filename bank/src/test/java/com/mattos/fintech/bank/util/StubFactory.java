package com.mattos.fintech.bank.util;

import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.CreditCardPurchase;
import com.mattos.fintech.bank.domain.transaction.DepositTransaction;
import com.mattos.fintech.bank.domain.transaction.WithdrawTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StubFactory {

    public static CreditCardAccount stubCreditCardAccount(String taxIdNumber, String stubnumber, String line1, String city, String state, String country, String zipCode, String name, BigDecimal balance, AccountState accountState) {
        return ((CreditCardAccount) AccountType.CREDIT_CARD.getInstance(stubnumber, name, new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode))
                .withState(accountState))
                .withIssuer(IssuerCompany.VISA)
                .withBalance(balance);
    }

    public static CreditCardPayment stubCreditCardPayment(BigDecimal amount, LocalDate paymentDate, String comment) {
        return CreditCardPayment.builder().amount(amount).transactionDate(paymentDate).build();
    }

    public static CreditCardPurchase stubCreditCardPurchase(BigDecimal amount, LocalDate purchaseDate, String comment) {
        return CreditCardPurchase.builder().amount(amount).transactionDate(purchaseDate).build();
    }

    public static CheckingAccount stubCheckingAccount(String taxIdNumber, String stubnumber, String line1, String city, String state, String country, String zipCode, String name, BigDecimal balance, AccountState accountState) {
        return ((CheckingAccount) AccountType.CHECKING.getInstance(stubnumber, name, new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode))
                .withState(accountState))
                .withBalance(balance);
    }

    public static DepositTransaction stubDeposit(BigDecimal amount, LocalDate depositDate, String comment, CheckingAccount account) {
        return DepositTransaction.builder().transactionDate(depositDate).amount(amount).comment(comment).targetAccount(account).build();
    }

    public static WithdrawTransaction stubWithdraw(BigDecimal amount, LocalDate depositDate, String comment, CheckingAccount account) {
        return WithdrawTransaction.builder().transactionDate(depositDate).amount(amount).comment(comment).targetAccount(account).build();
    }

    public static SavingsAccount stubSavingsAccount(String taxIdNumber, String stubnumber, String line1, String city, String state, String country, String zipCode, String name, BigDecimal balance, AccountState accountState) {
        return ((SavingsAccount) AccountType.SAVINGS.getInstance(stubnumber, name, new AccountHolder(taxIdNumber, "PERSON")
                .withBillingAddress(line1, city, state, country, zipCode))
                .withState(accountState))
                .withBalance(balance);
    }

    public static DepositTransaction stubDeposit(BigDecimal amount, LocalDate depositDate, String comment, SavingsAccount account) {
        return DepositTransaction.builder().transactionDate(depositDate).amount(amount).comment(comment).targetAccount(account).build();
    }

    public static WithdrawTransaction stubWithdraw(BigDecimal amount, LocalDate depositDate, String comment, SavingsAccount account) {
        return WithdrawTransaction.builder().transactionDate(depositDate).amount(amount).comment(comment).targetAccount(account).build();
    }
}
