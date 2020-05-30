package com.mattos.fintech.bank.domain.account;

import com.mattos.fintech.bank.domain.holder.AccountHolder;
import com.mattos.fintech.bank.domain.transaction.CreditCardPayment;
import com.mattos.fintech.bank.domain.transaction.CreditCardPurchase;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
@Document
public class CreditCardAccount extends Account {

    private IssuerCompany issuerCompany;
    private Integer ccvCode;
    private Integer goodThroughMonth;
    private Integer goodThroughYear;
    private BigDecimal limitAmount;
    private BigDecimal currentBalance;

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

    public CreditCardAccount withLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
        return this;
    }

    public CreditCardAccount withBalance(BigDecimal balance) {
        this.currentBalance = balance;
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

    public Boolean pay(CreditCardPayment newPayment){
        this.currentBalance = this.currentBalance.subtract(newPayment.getAmount());
        addTransaction(newPayment);
        return true;
    }

    public Boolean purchase(CreditCardPurchase newPurchase){

        this.currentBalance = this.currentBalance.add(newPurchase.getAmount());
        addTransaction(newPurchase);
        return true;
    }

    public Boolean revertPay(CreditCardPayment payment){
        this.currentBalance = this.currentBalance.add(payment.getAmount());
        addTransaction(payment);
        return true;
    }

    public Boolean revertPurchase(CreditCardPurchase purchase){
        this.currentBalance = this.currentBalance.subtract(purchase.getAmount());
        addTransaction(purchase);
        return true;
    }

}
