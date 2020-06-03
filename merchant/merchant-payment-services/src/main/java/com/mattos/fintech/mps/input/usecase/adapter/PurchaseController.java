package com.mattos.fintech.mps.input.usecase.adapter;

import com.mattos.fintech.mps.input.usecase.port.PayWithCard;
import com.mattos.fintech.mps.input.usecase.port.PurchaseInfo;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class PurchaseController {

    private PayWithCard payWithCardPort;

    @Autowired
    public PurchaseController(PayWithCard payWithCardPort) {
        this.payWithCardPort = payWithCardPort;
    }

    @PostMapping(path = "/purchases")
    public Mono<ResponseEntity<String>> creditCardPurchase(@RequestBody PurchaseInfo purchaseInfo){

        return payWithCardPort.requestAuthorization(Mono.just(purchaseInfo))
                .map(request -> new ResponseEntity<>(request, HttpStatus.CREATED));
    }



}
