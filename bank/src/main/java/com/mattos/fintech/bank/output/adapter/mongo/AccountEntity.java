package com.mattos.fintech.bank.output.adapter.mongo;

import com.mattos.fintech.bank.domain.account.*;
import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.holder.Person;
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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountHolderEntity getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolderEntity accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuerCompany() {
        return issuerCompany;
    }

    public void setIssuerCompany(String issuerCompany) {
        this.issuerCompany = issuerCompany;
    }

    public Integer getCcvCode() {
        return ccvCode;
    }

    public void setCcvCode(Integer ccvCode) {
        this.ccvCode = ccvCode;
    }

    public Integer getGoodThroughMonth() {
        return goodThroughMonth;
    }

    public void setGoodThroughMonth(Integer goodThroughMonth) {
        this.goodThroughMonth = goodThroughMonth;
    }

    public Integer getGoodThroughYear() {
        return goodThroughYear;
    }

    public void setGoodThroughYear(Integer goodThroughYear) {
        this.goodThroughYear = goodThroughYear;
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
        CreditCardAccount account = (CreditCardAccount)accountType.getInstance(accountEntity.getAccountNumber(), accountEntity.getName(), accountHolder );
        account.withIssuer(IssuerCompany.valueOf(accountEntity.getIssuerCompany()));
        account.withState(AccountState.valueOf(accountEntity.getState()));
        account.withCcvCode(accountEntity.getCcvCode());
        account.withGoodThroughMonth(accountEntity.getGoodThroughMonth());
        account.withGoodThroughYear(accountEntity.getGoodThroughYear());
        return account;
    }
}
