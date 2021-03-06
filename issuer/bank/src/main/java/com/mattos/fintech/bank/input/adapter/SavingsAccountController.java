package com.mattos.fintech.bank.input.adapter;

import com.mattos.fintech.bank.application.service.crud.QueryAccountsService;
import com.mattos.fintech.bank.input.query.port.BankingAccountInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.BankingAccountRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.OpenAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class SavingsAccountController {

    private final OpenAccount openAccountPort;
    private final QueryAccountsService queryAccountsPort;

    @Autowired
    public SavingsAccountController(OpenAccount openAccountPort, QueryAccountsService queryAccountsPort) {
        this.openAccountPort = openAccountPort;
        this.queryAccountsPort = queryAccountsPort;
    }

    @PostMapping(path="/savings")
    public Mono<ResponseEntity<String>> save(@RequestBody BankingAccountRequestInfo requestInfo) {
        return openAccountPort.openSavingsAccount(requestInfo)
                .map(savedAccount -> new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
    }

    @GetMapping(path="/savings")
    public Flux<BankingAccountInfo> listAll(@RequestParam String accountHolderId) {
        return queryAccountsPort.listAllOpenSavingsAccounts(accountHolderId);
    }



}
