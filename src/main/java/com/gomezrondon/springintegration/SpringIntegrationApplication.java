package com.gomezrondon.springintegration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	@Qualifier(value = BasicIntegrationConfig.INPUT_CHANNEL) // this is necessary
	private MessageChannel channel;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<Person> list = Arrays.asList(new Person("javier", "gomez"), new Person("pedro", "perez"));

		list.forEach(person -> {
			Message<?> message = MessageBuilder.withPayload(person).build();
			channel.send(message);
		});



	}
}
