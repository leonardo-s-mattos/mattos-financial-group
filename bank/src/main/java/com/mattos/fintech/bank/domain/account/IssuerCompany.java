package com.mattos.fintech.bank.domain.account;

public enum IssuerCompany {

    AMEX {
        @Override
        String MII(){
            return "3";
        }
    },
    VISA{
        @Override
        String MII(){
            return "3";
        }
    },
    MASTERCARD{
        @Override
        String MII(){
            return "5";
        }
    },
    DISCOVER{
        @Override
        String MII(){
            return "6";
        }
    };

    abstract String MII();

}
