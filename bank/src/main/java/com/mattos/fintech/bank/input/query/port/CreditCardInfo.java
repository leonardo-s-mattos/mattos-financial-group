package com.mattos.fintech.bank.input.query.port;

public class CreditCardInfo {

    private String creditCardNumber;
    private String creditCardLastDigits;
    private Integer goodThoughMonth;
    private Integer goodThoughYear;

    public CreditCardInfo(String creditCardNumber, String creditCardLastDigits, Integer goodThoughMonth, Integer goodThoughYear) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardLastDigits = creditCardLastDigits;
        this.goodThoughMonth = goodThoughMonth;
        this.goodThoughYear = goodThoughYear;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardLastDigits() {
        return creditCardLastDigits;
    }

    public void setCreditCardLastDigits(String creditCardLastDigits) {
        this.creditCardLastDigits = creditCardLastDigits;
    }

    public Integer getGoodThoughMonth() {
        return goodThoughMonth;
    }

    public void setGoodThoughMonth(Integer goodThoughMonth) {
        this.goodThoughMonth = goodThoughMonth;
    }

    public Integer getGoodThoughYear() {
        return goodThoughYear;
    }

    public void setGoodThoughYear(Integer goodThoughYear) {
        this.goodThoughYear = goodThoughYear;
    }
}
