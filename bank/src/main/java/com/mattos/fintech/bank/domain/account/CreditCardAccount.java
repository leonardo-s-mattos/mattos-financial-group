package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CreditCardAccount extends Account {

    private IssuerCompany issuerCompany;
    private Integer ccvCode;
    private Integer goodThroughMonth;
    private Integer goodThroughYear;
    private BigDecimal limitAmount;
    private BigDecimal currentBalance;

    public CreditCardAccount(String name, AccountHolder accountHolder) {
        super(AccountType.CREDIT_CARD, name, accountHolder);
        withState(AccountState.PENDING);
        ccvCode = new Random().nextInt(1000);
        goodThroughMonth = LocalDate.now().getMonthValue();
        goodThroughYear = LocalDate.now().getYear() + 4;
    }

    public CreditCardAccount(String accountNumber, String name, AccountHolder accountHolder) {
        super(accountNumber, AccountType.CREDIT_CARD, name, accountHolder);
    }

    public CreditCardAccount withIssuer(IssuerCompany issuerCompany){
        this.issuerCompany = issuerCompany;
        return this;
    }

    public CreditCardAccount withCcvCode(Integer ccvCode) {
        this.ccvCode = ccvCode;
        return this;
    }

    public CreditCardAccount withGoodThroughMonth(Integer goodThroughMonth) {
        this.goodThroughMonth = goodThroughMonth;
        return this;
    }

    public CreditCardAccount withGoodThroughYear(Integer goodThroughYear) {
        this.goodThroughYear = goodThroughYear;
        return this;
    }

    private String IIN(){
        return "14720";
    }

    public CreditCardAccount withAccountNumber(){
        String random = String.valueOf(new Random().nextLong());
        super.withNumber(issuerCompany.MII() + IIN() + random.substring(random.length()-10));
        return this;
    }

    public IssuerCompany getIssuerCompany() {
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

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
}
