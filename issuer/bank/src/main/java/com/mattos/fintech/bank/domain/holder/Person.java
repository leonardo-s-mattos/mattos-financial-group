package com.mattos.fintech.bank.domain.holder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AccountHolder {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, String idNumber) {
        super(idNumber, "Consumer");
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
