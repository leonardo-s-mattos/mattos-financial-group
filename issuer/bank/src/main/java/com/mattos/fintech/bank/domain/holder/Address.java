package com.mattos.fintech.bank.domain.holder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@AllArgsConstructor
public class Address {

    private String line1;
    private String city;
    private String state;
    private String country;
    private String zip;

}
