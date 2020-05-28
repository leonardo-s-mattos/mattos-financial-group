package com.mattos.fintech.bank.output.adapter;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountHolderEntity {

    @Id
    private String taxIdNumber;
    private String holderType;
    private String billingAddressLine1;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressCountry;
    private String billingAddressZip;

    public AccountHolderEntity(String taxIdNumber, String holderType, String billingAddressLine1, String billingAddressCity, String billingAddressState, String billingAddressCountry, String billingAddressZip) {
        this.taxIdNumber = taxIdNumber;
        this.holderType = holderType;
        this.billingAddressLine1 = billingAddressLine1;
        this.billingAddressCity = billingAddressCity;
        this.billingAddressState = billingAddressState;
        this.billingAddressCountry = billingAddressCountry;
        this.billingAddressZip = billingAddressZip;
    }

    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    public String getHolderType() {
        return holderType;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public String getBillingAddressZip() {
        return billingAddressZip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolderEntity that = (AccountHolderEntity) o;
        return Objects.equal(taxIdNumber, that.taxIdNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taxIdNumber);
    }
}
