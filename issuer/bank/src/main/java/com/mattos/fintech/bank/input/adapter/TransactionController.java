package com.mattos.fintech.bank.input.adapter;


import com.mattos.fintech.bank.application.service.crud.QueryAccountsService;
import com.mattos.fintech.bank.application.service.crud.QueryTransactionService;
import com.mattos.fintech.bank.input.query.port.CreditCardInfo;
import com.mattos.fintech.bank.input.query.port.GetTransaction;
import com.mattos.fintech.bank.input.query.port.TransactionInfo;
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
@RequestMapping("/transactions")
public class TransactionController {

    private final GetTransaction queryTransactionServicePort;

    @Autowired
    public TransactionController(GetTransaction queryTransactionServicePort) {
        this.queryTransactionServicePort = queryTransactionServicePort;
    }

    @GetMapping(path="/last")
    public Flux<TransactionInfo> getLast5TransactionsFor(@RequestParam String accountNumber) {
        return queryTransactionServicePort.getLastTransactions(accountNumber);
    }




}
