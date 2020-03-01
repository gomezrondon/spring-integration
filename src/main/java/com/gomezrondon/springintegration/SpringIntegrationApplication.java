package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	private PrinterGateway printerGateway;


	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<Person> list = Arrays.asList(new Person("javier", "gomez"), new Person("pedro", "perez"), new Person("andres", "torrez"));
		// sending a message to a Gateway -> SA uppercase -> SA print message (return void)
		// the message has the replyChannel
		list.forEach(person -> {
			Message<Person> message = MessageBuilder.withPayload(person)
					.setHeader("replyChannel", BasicIntegrationConfig.OUTPUT_CHANNEL)
					.build();
			printerGateway.print(message);
		});

	}
}
