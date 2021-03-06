package com.mattos.fintech.bank.input.adapter;


import com.mattos.fintech.bank.application.service.crud.QueryAccountsService;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;

import com.mattos.fintech.bank.input.usecase.port.banking.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.OpenAccount;
import com.mattos.fintech.bank.input.usecase.port.banking.PayCreditCard;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class CreditCardAccountController {

    private final OpenAccount openAccountPort;
    private final QueryAccountsService queryAccountsPort;
    private final PayCreditCard payCreditCardPort;

    @Autowired
    public CreditCardAccountController(OpenAccount openAccountPort, QueryAccountsService queryAccountsPort, PayCreditCard payCreditCardPort) {
        this.openAccountPort = openAccountPort;
        this.queryAccountsPort = queryAccountsPort;
        this.payCreditCardPort = payCreditCardPort;
    }

    @PostMapping(path="/creditcards")
    public Mono<ResponseEntity<String>> save(@RequestBody CreditCardRequestInfo requestInfo) {
        return openAccountPort.openCreditCardAccount(requestInfo)
                .map(savedAccount -> new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
    }

    @GetMapping(path="/creditcards")
    public Flux<CreditCardInfo> listAll( @RequestParam String accountHolderId) {
        return queryAccountsPort.listAllOpenCards(accountHolderId);
    }

    @GetMapping(path="/creditcards/{creditCardNumber}")
    public Mono<CreditCardInfo> one( @RequestParam String creditCardNumber) {
        return queryAccountsPort.findCardById(creditCardNumber);
    }

    @PostMapping(path="/creditcards/payment")
    public Mono<ResponseEntity<String>> pay(@RequestBody TransactionRequestInfo requestInfo) {
        return payCreditCardPort.pay(Mono.just(requestInfo))
                .map(savedTransaction -> new ResponseEntity<>(savedTransaction, HttpStatus.CREATED));
    }

    @PostMapping(path="/creditcards/payment/cancel")
    public Mono<ResponseEntity<String>> revert(@RequestBody TransactionRequestInfo requestInfo) {
        return payCreditCardPort.revert(Mono.just(requestInfo))
                .map(savedTransaction -> new ResponseEntity<>(savedTransaction, HttpStatus.CREATED));
    }


}
