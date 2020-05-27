package com.mattos.fintech.bank.domain.holder;

public class AccountHolder {

    private String taxIdNumber;
    private String holderType;
    private Address billingAddress;

    public AccountHolder(String taxIdNumber, String holderType){
        this.taxIdNumber = taxIdNumber;
        this.holderType = holderType;
    }

    public String getIdNumber() {
        return taxIdNumber;
    }

    public String getHolderType() {
        return holderType;
    }
}
