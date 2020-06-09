package com.mattos.fintech.bank.domain.holder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@Getter
@EqualsAndHashCode
public class Company extends AccountHolder {
    
    private String companyName;

    public Company(String companyName, String taxId) {
        super(taxId, "Commercial");
        this.companyName = companyName;
    }


}
