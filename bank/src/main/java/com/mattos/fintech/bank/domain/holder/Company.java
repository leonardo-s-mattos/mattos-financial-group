package com.mattos.fintech.bank.domain.holder;

import java.util.Objects;

public class Company extends AccountHolder {
    
    private String companyName;

    public Company(String companyName, String taxId) {
        super(taxId, "Commercial");
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyName.equals(company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName);
    }

    public String getCompanyName() {

        return companyName;
    }
}
