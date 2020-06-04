package com.mattos.fintech.authorization.output.adapter.redis;

import com.mattos.fintech.authorization.domain.PurchaseInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseInfoCrudRedisRepository extends CrudRepository<PurchaseInfo, String> {
}
