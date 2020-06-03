package com.mattos.fintech.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AuthorizationProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationProcessorApplication.class, args);
	}

}
