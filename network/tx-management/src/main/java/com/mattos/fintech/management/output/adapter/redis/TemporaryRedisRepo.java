package com.mattos.fintech.management.output.adapter.redis;

import com.mattos.fintech.management.input.port.PurchaseApprovalCommand;
import com.mattos.fintech.management.output.port.TemporaryCacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TemporaryRedisRepo implements TemporaryCacheRepo {

    private CrudRedisRepository redisRepo;

    @Autowired
    public TemporaryRedisRepo(CrudRedisRepository redisRepo) {
        this.redisRepo = redisRepo;
    }

    @Override
    public void pushToCache(PurchaseApprovalCommand purchaseInfo) {
        redisRepo.save(purchaseInfo);
    }

    @Override
    public Optional<PurchaseApprovalCommand> pullFromCache(String purchaseId) {

        Optional<PurchaseApprovalCommand> purchase = redisRepo.findById(purchaseId);
        redisRepo.deleteById(purchaseId);
        return purchase;

    }
}
