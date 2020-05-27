package com.mattos.fintech.bank.input.adapter;

import com.mattos.fintech.bank.domain.account.CreditCardAccount;
import com.mattos.fintech.bank.input.usecase.port.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.OpenAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final OpenAccount openAccountPort;
    private final OpenAccount openAccountPort;

    @Autowired
    public AccountController(OpenAccount openAccountPort) {
        this.openAccountPort = openAccountPort;
    }

    @PostMapping(path="/creditcards")
    public Mono<ResponseEntity<String>> save(@RequestBody CreditCardRequestInfo requestInfo) {
        return openAccountPort.openCreditCardAccount(requestInfo)
                .map(savedAccount -> new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
    }

    @GetMapping(path="/creditcards")
    public Flux<CreditCardAccountView> save(@RequestBody CreditCardRequestInfo requestInfo) {
        return openAccountPort.openCreditCardAccount(requestInfo)
                .map(savedAccount -> new ResponseEntity<>(savedAccount, HttpStatus.CREATED));
    }

}
