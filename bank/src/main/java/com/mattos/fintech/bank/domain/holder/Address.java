package com.mattos.fintech.bank.domain.holder;

public class Address {

    private String line1;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address(String line1, String city, String state, String country, String zip) {
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public String getLine1() {
        return line1;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }
}
