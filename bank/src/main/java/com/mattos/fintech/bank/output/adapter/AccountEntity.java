package com.mattos.fintech.bank.output.adapter;

import com.google.common.base.Objects;
import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountEntity {

    @Id
    private String accountNumber;
    private AccountHolderEntity accountHolder;
    private String accountType;
    private String state;
    private String name;


    private String issuerCompany;
    private Integer ccvCode;
    private Integer goodThroughMonth;
    private Integer goodThroughYear;

    public AccountEntity(String accountNumber, AccountHolderEntity accountHolder, String accountType, String state,
                         String name, String issuerCompany, Integer ccvCode, Integer goodThroughMonth,
                         Integer goodThroughYear) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.state = state;
        this.name = name;
        this.issuerCompany = issuerCompany;
        this.ccvCode = ccvCode;
        this.goodThroughMonth = goodThroughMonth;
        this.goodThroughYear = goodThroughYear;
    }

    public AccountEntity() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountHolderEntity getAccountHolder() {
        return accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getIssuerCompany() {
        return issuerCompany;
    }

    public Integer getCcvCode() {
        return ccvCode;
    }

    public Integer getGoodThroughMonth() {
        return goodThroughMonth;
    }

    public Integer getGoodThroughYear() {
        return goodThroughYear;
    }

    public static AccountEntity fromCreditCardAccount(Account account) {
        AccountHolderEntity accountHolderEntity =
                new AccountHolderEntity(account.getAccountHolder().getTaxIdNumber(),
                        account.getAccountHolder().getHolderType(),
                        account.getAccountHolder().getBillingAddress().getLine1(),
                        account.getAccountHolder().getBillingAddress().getCity(),
                        account.getAccountHolder().getBillingAddress().getState(),
                        account.getAccountHolder().getBillingAddress().getCountry(),
                        account.getAccountHolder().getBillingAddress().getZip());
        return new AccountEntity( account.getAccountNumber(), accountHolderEntity,
                account.getAccountType().name(), account.getState().name(),
                account.getName(), ((CreditCardAccount)account).getIssuerCompany().name(),
                ((CreditCardAccount)account).getCcvCode(), ((CreditCardAccount)account).getGoodThroughMonth(),
                ((CreditCardAccount)account).getGoodThroughYear());
    }

    public static Account fromAccountEntity(AccountEntity accountEntity) {

        AccountType accountType = AccountType.valueOf(accountEntity.getAccountType());
        // for now just CreditCard

        AccountHolder accountHolder = new AccountHolder(accountEntity.getAccountHolder().getTaxIdNumber(),
                                                        accountEntity.getAccountHolder().getHolderType());
        accountHolder.withBillingAddress(accountEntity.getAccountHolder().getBillingAddressLine1(),
                accountEntity.getAccountHolder().getBillingAddressCity(),
                accountEntity.getAccountHolder().getBillingAddressState(),
                accountEntity.getAccountHolder().getBillingAddressCountry(),
                accountEntity.getAccountHolder().getBillingAddressZip());
        CreditCardAccount account = ((CreditCardAccount)accountType.getInstance(accountEntity.getAccountNumber(), accountEntity.getName(), accountHolder ))
            .withIssuer(IssuerCompany.valueOf(accountEntity.getIssuerCompany()))
            .withCcvCode(accountEntity.getCcvCode())
            .withGoodThroughMonth(accountEntity.getGoodThroughMonth())
            .withGoodThroughYear(accountEntity.getGoodThroughYear());

        account.withState(AccountState.valueOf(accountEntity.getState()));

        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equal(accountNumber, that.accountNumber) &&
                Objects.equal(accountHolder, that.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber, accountHolder);
    }
}
