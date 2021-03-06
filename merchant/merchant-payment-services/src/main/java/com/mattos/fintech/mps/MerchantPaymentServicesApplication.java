package com.mattos.fintech.mps;

import com.mattos.fintech.mps.queues.Authorization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Authorization.class)
public class MerchantPaymentServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantPaymentServicesApplication.class, args);
	}

}
