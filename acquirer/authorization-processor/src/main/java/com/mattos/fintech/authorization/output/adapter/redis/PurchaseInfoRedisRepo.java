package com.mattos.fintech.authorization.output.adapter.redis;

import com.mattos.fintech.authorization.domain.PurchaseInfo;
import com.mattos.fintech.authorization.output.port.PurchaseInfoCacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PurchaseInfoRedisRepo implements PurchaseInfoCacheRepo {

    private PurchaseInfoCrudRedisRepository redisRepo;

    @Autowired
    public PurchaseInfoRedisRepo(PurchaseInfoCrudRedisRepository redisRepo) {
        this.redisRepo = redisRepo;
    }

    @Override
    public void pushToCache(PurchaseInfo purchaseInfo) {
        redisRepo.save(purchaseInfo);
    }

    @Override
    public Optional<PurchaseInfo> pullFromCache(String purchaseId) {
        return redisRepo.findById(purchaseId);
    }
}
