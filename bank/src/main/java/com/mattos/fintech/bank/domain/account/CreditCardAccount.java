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
        super(AccountType.CREDIT_CARD, AccountState.OPEN, name, accountHolder);
        ccvCode = new Random().nextInt(1000);
        goodThroughMonth = LocalDate.now().getMonthValue();
        goodThroughYear = LocalDate.now().getYear() + 4;
    }

    public void withIssuer(IssuerCompany issuerCompany){
        this.issuerCompany = issuerCompany;
        super.withNumber(generateAccountNumbers(issuerCompany));
    }

    private String IIN(){
        return "14720";
    }

    private String generateAccountNumbers(IssuerCompany issuerCompany){
        String random = String.valueOf(new Random().nextLong());
        return issuerCompany.MII() + IIN() + random.substring(random.length()-10);
    }

}
