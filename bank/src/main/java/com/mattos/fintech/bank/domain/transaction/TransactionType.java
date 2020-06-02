package com.mattos.fintech.bank.domain.transaction;

public enum TransactionType {

    CREDIT_CARD_PAYMENT {
        @Override
        public Transaction.TransactionBuilder builder(){
           return CreditCardPayment.builder();
        }
    },
    CREDIT_CARD_PAYMENT_CANCELED{
        @Override
        public Transaction.TransactionBuilder builder(){
            return CreditCardPaymentCancelled.builder();
        }
    },
    CREDIT_CARD_PURCHASE{
        @Override
        public Transaction.TransactionBuilder builder(){
            return CreditCardPurchase.builder();
        }
    },
    CREDIT_CARD_PURCHASE_CANCELLED{
        @Override
        public Transaction.TransactionBuilder builder(){
            return CreditCardPurchaseCancelled.builder();
        }
    };


    public abstract Transaction.TransactionBuilder builder();
}
