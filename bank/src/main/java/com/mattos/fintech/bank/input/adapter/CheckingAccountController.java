package com.mattos.fintech.bank.input.adapter;

import com.mattos.fintech.bank.application.service.QueryAccountsService;
import com.mattos.fintech.bank.input.query.port.BankingAccountInfo;
import com.mattos.fintech.bank.input.usecase.port.BankingAccountRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.OpenAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class CheckingAccountController {

    private final OpenAccount openAccountPort;
    private final QueryAccountsService queryAccountsPort;

    @Autowired
    public CheckingAccountController(OpenAccount openAccountPort, QueryAccountsService queryAccountsPort) {
        this.openAccountPort = openAccountPort;
        this.queryAccountsPort = queryAccountsPort;
    }

    @PostMapping(path="/checking")
    public Mono<ResponseEntity<String>> save(@RequestBody BankingAccountRequestInfo requestInfo) {
        return openAccountPort.openCheckingAccount(requestInfo)
                .map(savedAccount -> new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
    }

    @GetMapping(path="/checking")
    public Flux<BankingAccountInfo> listAll(@RequestParam String accountHolderId) {
        return queryAccountsPort.listAllOpenCheckingAccounts(accountHolderId);
    }



}
