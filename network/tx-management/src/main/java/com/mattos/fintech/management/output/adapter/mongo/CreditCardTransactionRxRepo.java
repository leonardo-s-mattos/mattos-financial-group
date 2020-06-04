package com.mattos.fintech.management.output.adapter.mongo;

import com.mattos.fintech.management.domain.CreditCardTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardTransactionRxRepo extends ReactiveMongoRepository<CreditCardTransaction, UUID> {
}
