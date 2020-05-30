package com.mattos.fintech.bank.domain.holder;

import com.google.common.base.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
public class AccountHolder {

    @Id
    protected String taxIdNumber;
    protected String holderType;
    protected Address billingAddress;

    public AccountHolder(String taxIdNumber, String holderType){
        this.taxIdNumber = taxIdNumber;
        this.holderType = holderType;
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
