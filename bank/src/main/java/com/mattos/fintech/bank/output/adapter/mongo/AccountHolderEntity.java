package com.mattos.fintech.bank.output.adapter.mongo;

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

    public AccountHolderEntity() {
    }

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

    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getBillingAddressZip() {
        return billingAddressZip;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }
}
