package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;

import java.time.LocalDate;
import java.util.Random;

public class CreditCardAccount extends Account {

    private IssuerCompany issuerCompany;
    private Integer ccvCode;
    private Integer goodThroughMonth;
    private Integer goodThroughYear;

    public CreditCardAccount(String name, AccountHolder accountHolder) {
        super(AccountType.CREDIT_CARD, name, accountHolder);
        withState(AccountState.OPEN);
        ccvCode = new Random().nextInt(1000);
        goodThroughMonth = LocalDate.now().getMonthValue();
        goodThroughYear = LocalDate.now().getYear() + 4;
    }

    public CreditCardAccount(String accountNumber, String name, AccountHolder accountHolder) {
        super(accountNumber, AccountType.CREDIT_CARD, name, accountHolder);
    }

    public void withIssuer(IssuerCompany issuerCompany){
        this.issuerCompany = issuerCompany;
        super.withNumber(generateAccountNumbers(issuerCompany));
    }

    public void withCcvCode(Integer ccvCode) {
        this.ccvCode = ccvCode;
    }

    public void withGoodThroughMonth(Integer goodThroughMonth) {
        this.goodThroughMonth = goodThroughMonth;
    }

    public void withGoodThroughYear(Integer goodThroughYear) {
        this.goodThroughYear = goodThroughYear;
    }

    private String IIN(){
        return "14720";
    }

    private String generateAccountNumbers(IssuerCompany issuerCompany){
        String random = String.valueOf(new Random().nextLong());
        return issuerCompany.MII() + IIN() + random.substring(random.length()-10);
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
}
