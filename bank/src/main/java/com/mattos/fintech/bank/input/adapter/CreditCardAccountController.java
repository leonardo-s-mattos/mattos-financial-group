package com.mattos.fintech.bank.input.adapter;

import com.mattos.fintech.bank.application.service.crud.QueryAccountsService;
import com.mattos.fintech.bank.application.service.events.TransactionEvents;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.CreditCardRequestInfo;
import com.mattos.fintech.bank.input.usecase.port.banking.OpenAccount;
import com.mattos.fintech.bank.input.usecase.port.banking.PayCreditCard;
import com.mattos.fintech.bank.input.usecase.port.events.TransactionRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
@EnableBinding(TransactionEvents.NewTransactionEvent.class)
public class CreditCardAccountController {

    private final OpenAccount openAccountPort;
    private final QueryAccountsService queryAccountsPort;

    private TransactionEvents.NewTransactionEvent processor;

    @Autowired
    public CreditCardAccountController(OpenAccount openAccountPort, QueryAccountsService queryAccountsPort, TransactionEvents.NewTransactionEvent processor) {
        this.openAccountPort = openAccountPort;
        this.queryAccountsPort = queryAccountsPort;
        this.processor = processor;
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

    @PostMapping(path="/creditcards/payment")
    public Mono<ResponseEntity<String>> pay(@RequestBody TransactionRequestInfo requestInfo) {
        processor.newTransactionNotification().send(message(requestInfo.withId()));
        return Mono.just(new ResponseEntity<>("Payment" + requestInfo.getTransactionId() + "Posted", HttpStatus.CREATED));
    }

    @PostMapping(path="/creditcards/payment/cancel")
    public Mono<ResponseEntity<String>> revert(@RequestBody TransactionRequestInfo requestInfo) {
        processor.newTransactionNotification().send(message(requestInfo.withId()));
        return Mono.just(new ResponseEntity<>("Payment" + requestInfo.getTransactionId() + "Posted", HttpStatus.CREATED));
    }


    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }

}
