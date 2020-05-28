package com.mattos.fintech.bank.domain.holder;

import com.google.common.base.Objects;

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

    public AccountHolder withBillingAddress(String line1, String city, String state, String country, String zipCode){
        billingAddress = new Address(line1, city, state, country, zipCode);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolder that = (AccountHolder) o;
        return Objects.equal(taxIdNumber, that.taxIdNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taxIdNumber);
    }
}
