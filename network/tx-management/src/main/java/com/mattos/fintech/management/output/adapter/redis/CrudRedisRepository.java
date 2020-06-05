package com.mattos.fintech.management.output.adapter.redis;

import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRedisRepository extends CrudRepository<PurchaseApprovalCommand, String> {
}
