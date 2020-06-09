package com.mattos.fintech.bank.input.adapter;

import com.mattos.fintech.bank.input.query.port.TransactionInfo;
import com.mattos.fintech.bank.input.usecase.port.events.AuthenticationInfo;
import com.mattos.fintech.bank.queues.Authorization;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ApprovedTransactionsListener {

    @StreamListener
    public void registerTransaction(@Input(Authorization.TX_AUTHORIZATION_RESPONSE) TransactionInfo transactionInfo,
                                    @Input(Authorization.AUTHENTICATION_RESPONSE) AuthenticationInfo authenticationInfo){
        System.out.println(" received message : tx info" + transactionInfo.toString());
        System.out.println(" received message : auth info " + authenticationInfo.toString());

    }
}
