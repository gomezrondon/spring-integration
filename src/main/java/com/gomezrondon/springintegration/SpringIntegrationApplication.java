package com.gomezrondon.springintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.MessagingGateway;



@SpringBootApplication
public class SpringIntegrationApplication implements ApplicationRunner {

	@Autowired
	private CustomGateway gateway;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		gateway.print("hola javier");
	}
}
