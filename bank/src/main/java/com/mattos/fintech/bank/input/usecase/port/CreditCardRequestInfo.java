package com.mattos.fintech.bank.input.usecase.port;

import org.springframework.stereotype.Component;

@Component
public class CreditCardRequestInfo {

    String issuerCompany;
    String accountName;
    String accountHolderId;
    String firstName;
    String lastName;
    String streetAddress;
    String city;
    String state;
    String country;
    String zipCode;

    public CreditCardRequestInfo(){
        super();
    }

    public CreditCardRequestInfo(String issuerCompany, String accountName, String accountHolderId, String firstName, String lastName, String streetAddress, String city, String state, String country, String zipCode) {
        this.issuerCompany = issuerCompany;
        this.accountName = accountName;
        this.accountHolderId = accountHolderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getIssuerCompany() {
        return issuerCompany;
    }

    public void setIssuerCompany(String issuerCompany) {
        this.issuerCompany = issuerCompany;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
