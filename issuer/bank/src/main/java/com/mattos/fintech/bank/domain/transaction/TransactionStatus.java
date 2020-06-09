package com.mattos.fintech.bank.domain.transaction;

public enum TransactionStatus {

    POSTED {
        @Override
        public Boolean isReversed() {
            return false;
        }
    },
    PENDING {
        @Override
        public Boolean isReversed() {
            return false;
        }
    },
    REVERTED {
        @Override
        public Boolean isReversed() {
            return true;
        }
    };

    public abstract Boolean isReversed();
}
