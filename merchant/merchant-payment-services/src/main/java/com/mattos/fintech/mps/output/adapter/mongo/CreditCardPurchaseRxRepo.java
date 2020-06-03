package com.mattos.fintech.mps.output.adapter.mongo;

import com.mattos.fintech.mps.domain.CreditCardPurchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardPurchaseRxRepo extends ReactiveMongoRepository<CreditCardPurchase, String> {

}
