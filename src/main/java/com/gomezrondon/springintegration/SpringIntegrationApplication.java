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
	private EnhancedPrinterGateway printerGateway;


	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<Person> list = Arrays.asList(new Person("javier", "gomez"), new Person("pedro", "perez"), new Person("andres", "torrez"));

		list.forEach(person -> {
/*			String uppercase = printerGateway.uppercase(person);
			System.out.println("<<< "+uppercase);*/

			printerGateway.print(person);
		});

	}
}
