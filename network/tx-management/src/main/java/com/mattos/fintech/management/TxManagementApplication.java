package com.mattos.fintech.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Sink.class, Processor.class})
@SpringBootApplication
public class TxManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxManagementApplication.class, args);
	}

}
