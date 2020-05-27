package com.mattos.fintech.bank.domain.holder;

public class AccountHolder {

    private String taxIdNumber;
    private String holderType;
    private Address billingAddress;

    public AccountHolder(String taxIdNumber, String holderType){
        this.taxIdNumber = taxIdNumber;
        this.holderType = holderType;
    }

    public String getHolderType() {
        return holderType;
    }

    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void withBillingAddress(String line1, String city, String state, String country, String zipCode){
        billingAddress = new Address(line1, city, state, country, zipCode);
    }
}
